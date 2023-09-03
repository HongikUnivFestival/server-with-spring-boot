package com.hiufestainfo.domain.foodtruck.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class FoodTruckNotFoundException extends BaseException {

    public FoodTruckNotFoundException() {
        super(FoodTruckErrorCode.FoodTruck_NOT_FOUND);
    }
}