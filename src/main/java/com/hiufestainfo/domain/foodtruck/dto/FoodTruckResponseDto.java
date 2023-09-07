package com.hiufestainfo.domain.foodtruck.dto;

import com.hiufestainfo.domain.foodtruck.entity.FoodTruck;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodTruckResponseDto {
    private Boolean isAdmin;
    private List<FoodTruck> foodTrucks;

    public FoodTruckResponseDto(Boolean isAdmin, List<FoodTruck> foodTrucks) {
        this.isAdmin = isAdmin;
        this.foodTrucks = foodTrucks;
    }
}

