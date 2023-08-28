package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class ExpiredTokenException extends BaseException {
    public ExpiredTokenException(){
        super(GlobalErrorCode.EXPIRED_TOKEN);
    }
}
