package com.hiufestainfo.global.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private final BaseErrorCode errorCode;
}
