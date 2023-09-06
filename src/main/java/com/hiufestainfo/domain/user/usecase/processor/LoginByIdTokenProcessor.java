package com.hiufestainfo.domain.user.usecase.processor;

import com.hiufestainfo.domain.user.exception.NotSupportedLoginTypeException;
import com.hiufestainfo.global.annotation.Processor;
import com.hiufestainfo.global.exception.IncorrectIssuerTokenException;
import com.hiufestainfo.global.feign.oauth.kakao.KakaoProperties;
import com.hiufestainfo.global.feign.oauth.oidc.PublicKeyDto;
import com.hiufestainfo.global.feign.oauth.oidc.PublicKeysDto;
import com.hiufestainfo.global.jwt.JwtIdTokenProvider;
import com.hiufestainfo.global.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;


@Processor
@RequiredArgsConstructor
public class LoginByIdTokenProcessor {
    private final PublicKeyProcessor publicKeyProcessor;
    private final JwtIdTokenProvider jwtIdTokenProvider;

    private final KakaoProperties kakaoProperties;

    public UserInfoFromIdToken execute(String loginType, String idToken){
        String kid = jwtIdTokenProvider.getKid(idToken);
        PublicKeysDto publicKeys = new PublicKeysDto();
        String iss = new String();
        String aud = new String();
        switch (loginType) {
            case "kakao":
                PublicKeysDto keys = publicKeyProcessor.getCachedKakaoPublicKeys();
                publicKeys = keys;
                iss = kakaoProperties.getIss();
                aud = kakaoProperties.getAppKey();
                break;
            case "google":

                break;
            default:
                throw new NotSupportedLoginTypeException();
        }
        PublicKeyDto key = publicKeys.getKeys().stream()
                .filter(k -> k.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new IncorrectIssuerTokenException());
        return jwtIdTokenProvider.getUserInfo(idToken, publicKeyProcessor.generatePublicKey(key), iss, aud);
    }

}
