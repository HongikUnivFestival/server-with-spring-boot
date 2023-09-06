package com.hiufestainfo.domain.user.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
