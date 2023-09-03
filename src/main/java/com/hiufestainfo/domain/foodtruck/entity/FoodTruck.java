package com.hiufestainfo.domain.foodtruck.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@Table(name = "foodtruck")
public class FoodTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brandName;
    private String introduction;

    protected FoodTruck() {}
}
