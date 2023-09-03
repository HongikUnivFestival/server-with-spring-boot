package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class AccessDeniedRequestException extends BaseException {

    public AccessDeniedRequestException() {
        super(GlobalErrorCode.ACCESS_DENIED_REQUEST);
    }
}
