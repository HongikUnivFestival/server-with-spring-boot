package com.hiufestainfo.domain.example.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class ExampleNotFoundException extends BaseException {

    public ExampleNotFoundException() {
        super(ExampleErrorCode.EXAMPLE_NOT_FOUND);
    }
}
