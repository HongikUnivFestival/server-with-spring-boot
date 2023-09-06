package com.hiufestainfo.domain.user.service;

import com.hiufestainfo.domain.user.adaptor.UserAdaptor;
import com.hiufestainfo.domain.user.entity.AuthInfo;
import com.hiufestainfo.domain.user.entity.LoginType;
import com.hiufestainfo.domain.user.entity.Profile;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.domain.user.exception.EmailAlreadyRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDomainService {
    private final UserAdaptor userAdaptor;

    public User login(LoginType loginType, String email){
        User user = userAdaptor.findByEmail(email);
        if(user.getAuthInfo().getLoginType().equals(loginType)){
            return user;
        }else throw new EmailAlreadyRegistered();
    }

    @Transactional
    public User signUp(Profile profile, AuthInfo authInfo){
        User user = User.builder()
                .profile(profile)
                .authInfo(authInfo)
                .build();
        return userAdaptor.save(user);
    }

}
