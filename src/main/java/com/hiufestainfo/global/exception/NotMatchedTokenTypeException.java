package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class NotMatchedTokenTypeException extends BaseException {

    public NotMatchedTokenTypeException() {
        super(GlobalErrorCode.NOT_MATCHED_TOKEN_TYPE);
    }
}
