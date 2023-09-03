package com.hiufestainfo.domain.user.usecase;

import com.hiufestainfo.domain.user.adaptor.UserAdaptor;
import com.hiufestainfo.domain.user.dto.request.SignUpRequestDto;
import com.hiufestainfo.domain.user.dto.response.AccountTokenDto;
import com.hiufestainfo.domain.user.entity.AuthInfo;
import com.hiufestainfo.domain.user.entity.LoginType;
import com.hiufestainfo.domain.user.entity.Profile;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.domain.user.exception.EmailAlreadyRegistered;
import com.hiufestainfo.domain.user.service.UserDomainService;
import com.hiufestainfo.domain.user.usecase.processor.GenerateAccountTokenProcessor;
import com.hiufestainfo.domain.user.usecase.processor.LoginByIdTokenProcessor;
import com.hiufestainfo.global.annotation.UseCase;
import com.hiufestainfo.global.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SignUpUseCase {
    private final UserDomainService userDomainService;
    private final UserAdaptor userAdaptor;
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;

    public AccountTokenDto execute(String loginType,
                                   String idToken){
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userAdaptor.checkEmail(userInfo.getEmail())){
            Profile profile = Profile.profileForSignUp(userInfo.getNickname(),
                    userInfo.getProfileImage());

            AuthInfo authInfo = AuthInfo.authInfoForSignUp((LoginType.fromValue(loginType)), userInfo.getEmail());
            User user = userDomainService.signUp(profile, authInfo);
            return generateAccountTokenProcessor.createToken(user);
        }else throw new EmailAlreadyRegistered();
    }
}
