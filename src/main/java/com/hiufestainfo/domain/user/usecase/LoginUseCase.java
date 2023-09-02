package com.hiufestainfo.domain.user.usecase;

import com.hiufestainfo.domain.user.adaptor.UserAdaptor;
import com.hiufestainfo.domain.user.dto.response.AccountTokenDto;
import com.hiufestainfo.domain.user.entity.LoginType;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.domain.user.service.UserDomainService;
import com.hiufestainfo.domain.user.usecase.processor.GenerateAccountTokenProcessor;
import com.hiufestainfo.domain.user.usecase.processor.LoginByIdTokenProcessor;
import com.hiufestainfo.global.annotation.UseCase;
import com.hiufestainfo.global.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;

    public AccountTokenDto execute(String loginType, String idToken){
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userAdaptor.checkEmail(userInfo.getEmail())){
            return AccountTokenDto.notRegistered();
        }

        User user = userDomainService.login(LoginType.fromValue(loginType), userInfo.getEmail());
        return generateAccountTokenProcessor.createToken(user);
    }
}
