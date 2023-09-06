package com.hiufestainfo.domain.user.usecase;
import com.hiufestainfo.domain.user.adaptor.UserAdaptor;
import com.hiufestainfo.domain.user.dto.response.AccountTokenDto;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.domain.user.service.RefreshTokenRedisService;
import com.hiufestainfo.domain.user.usecase.processor.GenerateAccountTokenProcessor;
import com.hiufestainfo.global.annotation.UseCase;
import com.hiufestainfo.global.exception.InvalidTokenException;
import com.hiufestainfo.global.jwt.JwtProvider;
import com.hiufestainfo.global.jwt.dto.DecodedJwtToken;
import lombok.RequiredArgsConstructor;

import static com.hiufestainfo.global.constant.StaticValue.REFRESH_TOKEN;


@UseCase
@RequiredArgsConstructor
public class RefreshTokenUseCase {
    private final RefreshTokenRedisService refreshTokenRedisService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;
    private final JwtProvider jwtProvider;
    private final UserAdaptor userAdaptor;
    public AccountTokenDto execute(String refreshToken){
        DecodedJwtToken decodedJwtToken = jwtProvider.decodeToken(refreshToken,REFRESH_TOKEN);
        User user = userAdaptor.findById(decodedJwtToken.getUserId());

        if(refreshTokenRedisService.checkToken(user.getId(), refreshToken)){
            return generateAccountTokenProcessor.refreshToken(user,refreshToken);
        }else throw new InvalidTokenException();
    }
}
