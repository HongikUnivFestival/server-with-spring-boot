package com.hiufestainfo.domain.example.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class CreateExampleBadRequestException extends BaseException {


    public CreateExampleBadRequestException() {
        super(ExampleErrorCode.CREATE_EXAMPLE_BAD_REQUEST);
    }
}
