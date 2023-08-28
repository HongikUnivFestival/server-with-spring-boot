package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(){
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
