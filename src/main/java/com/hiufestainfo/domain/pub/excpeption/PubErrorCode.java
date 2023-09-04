package com.hiufestainfo.domain.pub.excpeption;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.NOT_FOUND;


@Getter
@AllArgsConstructor
public enum PubErrorCode implements BaseErrorCode {
    PUB_NOT_FOUND(NOT_FOUND, "PUB_404", "예시가 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
