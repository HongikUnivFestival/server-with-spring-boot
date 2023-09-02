package com.hiufestainfo.global.utils;

import com.hiufestainfo.domain.user.adaptor.UserAdaptor;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.global.config.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthentiatedUserUtils {
    private final UserAdaptor userAdaptor;

    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }

    public User getCurrentUser() {
        return userAdaptor.findById(getCurrentUserId());
    }
}
