package com.hiufestainfo.domain.user.usecase;

import com.hiufestainfo.domain.user.dto.response.IdTokenDto;
import com.hiufestainfo.domain.user.exception.NotSupportedLoginTypeException;
import com.hiufestainfo.global.annotation.UseCase;
import com.hiufestainfo.global.feign.oauth.kakao.KakaoProperties;
import com.hiufestainfo.global.feign.oauth.kakao.RequestKakaoTokenClient;
import com.hiufestainfo.global.feign.oauth.kakao.dto.KakaoTokenInfoDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RequestIdTokenUseCase {
    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final KakaoProperties kakaoProperties;
    public IdTokenDto execute(String loginType, String code){
        switch (loginType) {
            case "kakao":
                KakaoTokenInfoDto kakaoTokenInfoDto = requestKakaoTokenClient.getToken(
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrl(),
                        code,
                        kakaoProperties.getClientSecret());
                return IdTokenDto.of(kakaoTokenInfoDto.getIdToken());
            case "google":
                break;
        }
        throw new NotSupportedLoginTypeException();
    }
}
