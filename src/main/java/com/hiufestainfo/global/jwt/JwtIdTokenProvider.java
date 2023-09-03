package com.hiufestainfo.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiufestainfo.global.exception.ExpiredTokenException;
import com.hiufestainfo.global.exception.IncorrectIssuerTokenException;
import com.hiufestainfo.global.exception.InvalidSignatureTokenException;
import com.hiufestainfo.global.exception.InvalidTokenException;
import com.hiufestainfo.global.jwt.dto.UserInfoFromIdToken;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtIdTokenProvider {
    /* kid 서명검증없이 가져오기  */
    public String getKid(String idToken){
        try{
            String[] idTokenParts = idToken.split("\\.");
            String encodedHeader = idTokenParts[0];
            String decodedHeader = new String(Base64.getUrlDecoder().decode(encodedHeader), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(decodedHeader, Map.class);
            return map.get("kid");
        } catch (Exception e){
            throw new InvalidTokenException();
        }
    }

    /* iss, aud, 만료시간 검증 & 서명검증 & 유저정보 가져오기 */
    public UserInfoFromIdToken getUserInfo(String idToken, RSAPublicKey publicKey, String iss, String aud) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .requireIssuer(iss)
                    .requireAudience(aud)
                    .build()
                    .parseClaimsJws(idToken)
                    .getBody();
            return UserInfoFromIdToken.builder()
                    .profileImage(claims.get("profileImage", String.class))
                    .email(claims.get("email", String.class))
                    .build();

        } catch (SignatureException exception) {
            throw new InvalidSignatureTokenException();
        }catch (IncorrectClaimException exception){
            throw new IncorrectIssuerTokenException();
        }catch (ExpiredJwtException exception) {
            throw new ExpiredTokenException();
        } catch (Exception exception){
            throw new InvalidTokenException();
        }
    }



}