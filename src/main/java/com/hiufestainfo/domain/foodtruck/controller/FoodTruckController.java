package com.hiufestainfo.domain.foodtruck.controller;

import com.hiufestainfo.domain.foodtruck.dto.FoodTruckDto;
import com.hiufestainfo.domain.foodtruck.service.FoodTruckService;
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

    @PostMapping
    public ResponseEntity<String> createFoodTruck(@RequestBody FoodTruckDto foodTruckDto) {
        foodTruckService.createFoodTruck(foodTruckDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<FoodTruckDto>> getAllFoodTrucks() {
        List<FoodTruckDto> foodTrucks = foodTruckService.getAllFoodTrucks();
        return ResponseEntity.ok(foodTrucks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodTruckDto> getFoodTruck(@PathVariable Long id) {
        FoodTruckDto foodTruck = foodTruckService.getFoodTruck(id);
        return ResponseEntity.ok(foodTruck);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFoodTruck(@PathVariable Long id, @RequestBody FoodTruckDto foodTruckDto) {
        foodTruckService.updateFoodTruck(id, foodTruckDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodTruck(@PathVariable Long id) {
        foodTruckService.deleteFoodTruck(id);
        return ResponseEntity.ok().build();
    }
}
