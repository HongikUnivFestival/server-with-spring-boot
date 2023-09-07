package com.hiufestainfo.domain.promotion.exception;


import com.hiufestainfo.global.exception.base.BaseException;

public class CreatePromotionBadRequestException extends BaseException {


    public CreatePromotionBadRequestException() {
        super(PromotionErrorCode.CREATE_PROMOTION_BAD_REQUEST);
    }
}
