package com.hiufestainfo.domain.festival.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class FestivalNotFoundException extends BaseException {

    public FestivalNotFoundException() {
        super(FestivalErrorCode.Festival_NOT_FOUND);
    }
}