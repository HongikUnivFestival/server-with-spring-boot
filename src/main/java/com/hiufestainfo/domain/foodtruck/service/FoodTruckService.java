package com.hiufestainfo.domain.foodtruck.service;


import com.hiufestainfo.domain.foodtruck.dto.FoodTruckDto;
import com.hiufestainfo.domain.foodtruck.dto.FoodTruckResponseDto;
import com.hiufestainfo.domain.foodtruck.entity.FoodTruck;
import com.hiufestainfo.domain.foodtruck.exception.CreateFoodTruckBadRequestException;
import com.hiufestainfo.domain.foodtruck.exception.FoodTruckNotFoundException;
import com.hiufestainfo.domain.foodtruck.exception.FoodTruckServiceException;
import com.hiufestainfo.domain.foodtruck.repository.FoodTruckRepository;
import com.hiufestainfo.domain.user.entity.Role;
import com.hiufestainfo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public FoodTruckResponseDto getAllFoodTrucks(User user) {
        Boolean isAdmin = false;
        Role accountStatus = user.getAuthInfo().getRole();

        if ("GUEST".equals(accountStatus.getValue())) {
            isAdmin = false;
        } else if ("ADMIN".equals(accountStatus.getValue())) {
            isAdmin = true;
        }

        List<FoodTruck> foodTrucks = foodTruckRepository.findAll();

        if (foodTrucks == null) {
            foodTrucks = new ArrayList<>();
        }

        if (foodTrucks.isEmpty()) {
            throw new FoodTruckNotFoundException();
        }

        return new FoodTruckResponseDto(isAdmin, foodTrucks);

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
