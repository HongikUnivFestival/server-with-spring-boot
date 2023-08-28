package com.hiufestainfo.global.exception.base;

public interface BaseErrorCode {
    public String getCode();
    public String getMessage();
    public int getHttpStatus();
}
