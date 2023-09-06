package com.hiufestainfo.domain.user.usecase.processor;
import com.hiufestainfo.domain.user.dto.response.AccountTokenDto;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.domain.user.service.RefreshTokenRedisService;
import com.hiufestainfo.global.annotation.Processor;
import com.hiufestainfo.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class GenerateAccountTokenProcessor {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRedisService refreshTokenRedisService;

    public AccountTokenDto createToken(User user){
        String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getAuthInfo().getRole().getValue());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getAuthInfo().getRole().getValue());
        /* refreshToken 캐쉬에 저장 */
        refreshTokenRedisService.save(user.getId(), refreshToken);
        return AccountTokenDto.of(accessToken,refreshToken);
    }

    public AccountTokenDto refreshToken(User user, String refreshToken){
        String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getAuthInfo().getRole().getValue());
        return AccountTokenDto.of(accessToken,refreshToken);
    }
}
