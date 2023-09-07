package com.hiufestainfo.domain.djdetail.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class DjDetailServiceException extends BaseException {

    public DjDetailServiceException() {
        super(DjDetailErrorCode.CREATE_DjDetail_FAILED);
    }
}