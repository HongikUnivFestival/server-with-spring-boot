package com.hiufestainfo.domain.promotion.exception;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum PromotionErrorCode implements BaseErrorCode {
    CREATE_Promotion_BAD_REQUEST(BAD_REQUEST, "Promotion_400", "Promotion 부스를 생성하는 데 잘못된 요청을 하였습니다."),
    Promotion_NOT_FOUND(NOT_FOUND, "Promotion_404", "Promotion 부스가 존재하지 않습니다."),
    CREATE_Promotion_FAILED(INTERNAL_SERVER_ERROR, "Promotion_500", "Promotion 부스를 생성하는 중에 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
