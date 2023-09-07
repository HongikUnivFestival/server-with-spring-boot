package com.hiufestainfo.domain.promotion.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class PromotionServiceException extends BaseException {

    public PromotionServiceException() {
        super(PromotionErrorCode.CREATE_PROMOTION_FAILED);
    }
}
