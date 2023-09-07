package com.hiufestainfo.domain.foodtruck.repository;

import com.hiufestainfo.domain.foodtruck.entity.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {
}

