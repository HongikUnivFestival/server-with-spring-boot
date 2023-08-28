package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class IncorrectIssuerTokenException extends BaseException {
    public IncorrectIssuerTokenException() {
        super(GlobalErrorCode.INCORRECT_ISSUER_TOKEN);
    }
}
