package com.hiufestainfo.domain.foodtruck.exception;

import com.hiufestainfo.global.exception.base.BaseException;

public class CreateFoodTruckBadRequestException extends BaseException {


    public CreateFoodTruckBadRequestException() {
        super(FoodTruckErrorCode.CREATE_FOODTRUCK_BAD_REQUEST);
    }
}
