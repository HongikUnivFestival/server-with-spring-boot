package com.hiufestainfo.domain.user.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class EmailAlreadyRegistered extends BaseException {
    public EmailAlreadyRegistered() {
        super(UserErrorCode.EMAIL_ALREADY_REGISTERED);
    }
}
