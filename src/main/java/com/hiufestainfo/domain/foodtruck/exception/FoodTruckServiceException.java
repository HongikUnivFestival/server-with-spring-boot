package com.hiufestainfo.domain.foodtruck.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class FoodTruckServiceException extends BaseException {

    public FoodTruckServiceException() {
        super(FoodTruckErrorCode.CREATE_FoodTruck_FAILED);
    }
}
