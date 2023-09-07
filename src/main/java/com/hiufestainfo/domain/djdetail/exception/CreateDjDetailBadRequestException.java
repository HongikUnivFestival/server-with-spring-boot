package com.hiufestainfo.domain.djdetail.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class CreateDjDetailBadRequestException extends BaseException {


    public CreateDjDetailBadRequestException() {
        super(DjDetailErrorCode.CREATE_DjDetail_BAD_REQUEST);
    }
}