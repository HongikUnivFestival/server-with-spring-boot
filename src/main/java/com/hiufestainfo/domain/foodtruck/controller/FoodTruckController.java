package com.hiufestainfo.domain.foodtruck.controller;

import com.hiufestainfo.domain.foodtruck.dto.FoodTruckDto;
import com.hiufestainfo.domain.foodtruck.dto.FoodTruckResponseDto;
import com.hiufestainfo.domain.foodtruck.service.FoodTruckService;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.global.response.SuccessResponse;
import com.hiufestainfo.global.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodtrucks")
@RequiredArgsConstructor
public class FoodTruckController {
    private final FoodTruckService foodTruckService;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> createFoodTruck(@RequestBody FoodTruckDto foodTruckDto) {
        foodTruckService.createFoodTruck(foodTruckDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(message));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<FoodTruckResponseDto>> getAllFoodTruck() {
        User user = authentiatedUserUtils.getCurrentUser(); // 유저 정보 가져오기

        FoodTruckResponseDto foodTruckResponse = foodTruckService.getAllFoodTrucks(user);

        return ResponseEntity.ok(SuccessResponse.of(foodTruckResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<FoodTruckDto>> getFoodTruck(@PathVariable Long id) {
        FoodTruckDto foodTruck = foodTruckService.getFoodTruck(id);
        return ResponseEntity.ok(SuccessResponse.of(foodTruck));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> updateFoodTruck(@PathVariable Long id, @RequestBody FoodTruckDto foodTruckDto) {
        foodTruckService.updateFoodTruck(id, foodTruckDto);
        return ResponseEntity.ok(SuccessResponse.empty());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteFoodTruck(@PathVariable Long id) {
        foodTruckService.deleteFoodTruck(id);
        return ResponseEntity.ok(SuccessResponse.empty());
    }
}