package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class InvalidSignatureTokenException extends BaseException {
    public InvalidSignatureTokenException(){
        super(GlobalErrorCode.INVALID_SIGNATURE_TOKEN);
    }
}
