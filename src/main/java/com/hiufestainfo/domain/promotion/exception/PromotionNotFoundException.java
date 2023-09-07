package com.hiufestainfo.domain.promotion.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class PromotionNotFoundException extends BaseException {

    public PromotionNotFoundException() {
        super(PromotionErrorCode.PROMOTION_NOT_FOUND);
    }
}