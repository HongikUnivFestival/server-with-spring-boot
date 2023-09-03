package com.hiufestainfo.domain.foodtruck.exception;

import com.hiufestainfo.global.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hiufestainfo.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum FoodTruckErrorCode implements BaseErrorCode {
    CREATE_FoodTruck_BAD_REQUEST(BAD_REQUEST, "FOODTRUCK_400", "푸드트럭을 생성하는 데 잘못된 요청을 하였습니다."),
    FoodTruck_NOT_FOUND(NOT_FOUND, "FOODTRUCK_404", "푸드트럭이 존재하지 않습니다."),
    CREATE_FoodTruck_FAILED(INTERNAL_SERVER_ERROR, "EXAMPLE_500", "푸드트럭을 생성하는 중에 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
