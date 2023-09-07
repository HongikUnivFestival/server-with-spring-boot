package com.hiufestainfo.domain.booth.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class BoothNotFoundException  extends BaseException {
    public BoothNotFoundException() {
        super(BoothErrorCode.BOOTH_NOT_FOUND);
    }

}
