package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class PublicKeyGenerationException extends BaseException {


    public PublicKeyGenerationException() {
        super(GlobalErrorCode.PUBKEY_GENERATION_FAILED);
    }
}
