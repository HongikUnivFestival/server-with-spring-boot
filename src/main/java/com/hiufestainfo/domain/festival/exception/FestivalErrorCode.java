package com.hiufestainfo.domain.festival.exception;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum FestivalErrorCode implements BaseErrorCode {
    CREATE_Festival_BAD_REQUEST(BAD_REQUEST, "Festival_400", "Festival를 생성하는 데 잘못된 요청을 하였습니다."),
    Festival_NOT_FOUND(NOT_FOUND, "Festival_404", "Festival 정보가 존재하지 않습니다."),
    CREATE_Festival_FAILED(INTERNAL_SERVER_ERROR, "Promotion_500", "Festival를 생성하는 중에 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}