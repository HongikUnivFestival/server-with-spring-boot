package com.hiufestainfo.domain.booth.exception;

import com.hiufestainfo.domain.example.exception.ExampleErrorCode;
import com.hiufestainfo.global.exception.base.BaseException;

public class CreateBoothBadRequestException extends BaseException {
    public CreateBoothBadRequestException() {
        super(BoothErrorCode.CREATE_BOOTH_BAD_REQUEST);
    }
}
