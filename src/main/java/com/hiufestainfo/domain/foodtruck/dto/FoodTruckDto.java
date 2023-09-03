package com.hiufestainfo.domain.foodtruck.dto;

import com.hiufestainfo.domain.foodtruck.entity.FoodTruck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FoodTruckDto {

    private String brandName;
    private String introduction;

    public static FoodTruckDto fromEntity(FoodTruck foodTruck) {
        return FoodTruckDto.builder()
                .brandName(foodTruck.getBrandName())
                .introduction(foodTruck.getIntroduction())
                .build();
    }

    public FoodTruck toEntity() {
        return FoodTruck.builder()
                .brandName(this.brandName)
                .introduction(this.introduction)
                .build();
    }
}
