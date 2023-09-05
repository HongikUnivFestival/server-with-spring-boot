package com.hiufestainfo.domain.festival.exception;



import com.hiufestainfo.global.exception.base.BaseException;

public class CreateFestivalBadRequestException extends BaseException {


    public CreateFestivalBadRequestException() {
        super(FestivalErrorCode.CREATE_Festival_BAD_REQUEST);
    }
}