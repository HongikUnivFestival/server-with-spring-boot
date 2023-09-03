package com.hiufestainfo.domain.user.exception;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum RefreshTokenErrorCode implements BaseErrorCode {
    EXPIRED_REFRESH_TOKEN(UNAUTHORIZED, "REFRESH_TOKEN_401", "해당 리프레쉬토큰은 만료되었습니다.");


    private final int httpStatus;
    private final String code;
    private final String message;
}
