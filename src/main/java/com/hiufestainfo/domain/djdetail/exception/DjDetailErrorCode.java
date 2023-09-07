package com.hiufestainfo.domain.djdetail.exception;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum DjDetailErrorCode implements BaseErrorCode {
    CREATE_DjDetail_BAD_REQUEST(BAD_REQUEST, "DjDetail_400", "DjDetail를 생성하는 데 잘못된 요청을 하였습니다."),
    DjDetail_NOT_FOUND(NOT_FOUND, "DjDetail_404", "DjDetail 정보가 존재하지 않습니다."),
    CREATE_DjDetail_FAILED(INTERNAL_SERVER_ERROR, "DjDetail_500", "DjDetail를 생성하는 중에 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}