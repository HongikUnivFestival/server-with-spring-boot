package com.hiufestainfo.domain.user.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class ExpiredRefreshTokenException extends BaseException {
    public ExpiredRefreshTokenException() {
        super(RefreshTokenErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
