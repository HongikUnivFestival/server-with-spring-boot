package com.hiufestainfo.domain.djdetail.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class DjDetailNotFoundException extends BaseException {

    public DjDetailNotFoundException() {
        super(DjDetailErrorCode.DjDetail_NOT_FOUND);
    }
}