package com.hiufestainfo.global.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class NotAuthentiatedException extends BaseException {
    public NotAuthentiatedException() {
        super(GlobalErrorCode.NOT_AUTHENTIATED_ERROR);
    }
}
