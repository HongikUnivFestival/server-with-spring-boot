package com.hiufestainfo.domain.festival.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class FestivalServiceException extends BaseException {

    public FestivalServiceException() {
        super(FestivalErrorCode.CREATE_Festival_FAILED);
    }
}