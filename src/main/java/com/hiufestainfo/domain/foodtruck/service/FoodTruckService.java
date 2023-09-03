package com.hiufestainfo.domain.foodtruck.service;


import com.hiufestainfo.domain.foodtruck.dto.FoodTruckDto;
import com.hiufestainfo.domain.foodtruck.entity.FoodTruck;
import com.hiufestainfo.domain.foodtruck.exception.CreateFoodTruckBadRequestException;
import com.hiufestainfo.domain.foodtruck.exception.FoodTruckNotFoundException;
import com.hiufestainfo.domain.foodtruck.exception.FoodTruckServiceException;
import com.hiufestainfo.domain.foodtruck.repository.FoodTruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodTruckService {
    private final FoodTruckRepository foodTruckRepository;

    public void createFoodTruck(FoodTruckDto foodTruckDto) {
        FoodTruck newFoodTruck = foodTruckDto.toEntity();
        try {
            foodTruckRepository.save(newFoodTruck);
        } catch (DataAccessException ex) {
            throw new CreateFoodTruckBadRequestException();
        }
    }

    public FoodTruckDto getFoodTruck(Long id) {
        FoodTruck foodTruck = foodTruckRepository.findById(id)
                .orElseThrow(() -> new FoodTruckNotFoundException());
        return FoodTruckDto.fromEntity(foodTruck);
    }

    public List<FoodTruckDto> getAllFoodTrucks() {
        try {
            List<FoodTruck> foodTrucks = foodTruckRepository.findAll();
            if (foodTrucks.isEmpty()) {
                throw new FoodTruckNotFoundException();
            }
            return foodTrucks.stream()
                    .map(FoodTruckDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            throw new FoodTruckServiceException();
        }
    }

    public void updateFoodTruck(Long id, FoodTruckDto updatedFoodTruckDto) {
        FoodTruck foodTruckToUpdate = foodTruckRepository.findById(id)
                .orElseThrow(() -> new FoodTruckNotFoundException());

        foodTruckToUpdate = updatedFoodTruckDto.toEntity().toBuilder()
                .id(id)
                .build();

        try {
            foodTruckRepository.save(foodTruckToUpdate);
        } catch (DataAccessException ex) {
            throw new FoodTruckServiceException();
        }
    }

    public void deleteFoodTruck(Long id) {
        if (!foodTruckRepository.existsById(id)) {
            throw new FoodTruckNotFoundException();
        }
        try {
            foodTruckRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new FoodTruckServiceException();
        }
    }
}
