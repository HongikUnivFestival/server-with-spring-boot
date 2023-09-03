package com.hiufestainfo.domain.foodtruck.service;


import com.hiufestainfo.domain.foodtruck.dto.FoodTruckDto;
import com.hiufestainfo.domain.foodtruck.entity.FoodTruck;
import com.hiufestainfo.domain.foodtruck.repository.FoodTruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodTruckService {
    private final FoodTruckRepository foodTruckRepository;

    public void createFoodTruck(FoodTruckDto foodTruckDto) {
        FoodTruck newFoodTruck = foodTruckDto.toEntity();
        foodTruckRepository.save(newFoodTruck);
    }


    public FoodTruckDto getFoodTruck(Long id) {
        FoodTruck foodTruck = foodTruckRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));
        return FoodTruckDto.fromEntity(foodTruck);
    }

    public List<FoodTruckDto> getAllFoodTrucks() {
        List<FoodTruck> foodTrucks = foodTruckRepository.findAll();
        return foodTrucks.stream()
                .map(FoodTruckDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void updateFoodTruck(Long id, FoodTruckDto updatedFoodTruckDto) {
        FoodTruck foodTruckToUpdate = foodTruckRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));

        foodTruckToUpdate = updatedFoodTruckDto.toEntity().toBuilder()
                .id(id)
                .build();

        foodTruckRepository.save(foodTruckToUpdate);
    }

    public void deleteFoodTruck(Long id) {
        if (!foodTruckRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다.");
        }
        foodTruckRepository.deleteById(id);
    }

}

