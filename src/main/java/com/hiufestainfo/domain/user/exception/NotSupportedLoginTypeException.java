package com.hiufestainfo.domain.user.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class NotSupportedLoginTypeException extends BaseException {
    public NotSupportedLoginTypeException() {
        super(UserErrorCode.NOT_SUPPORTED_LOGIN_TYPE);
    }
}
