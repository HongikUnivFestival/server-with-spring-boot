package com.hiufestainfo.domain.promotion.dto;

import com.hiufestainfo.domain.promotion.entity.Promotion;
import lombok.Getter;

import java.util.List;

@Getter
public class PromotionResponseDto {
    private Boolean isAdmin;
    private List<Promotion> promotions;

    public PromotionResponseDto(Boolean isAdmin, List<Promotion> promotions) {
        this.isAdmin = isAdmin;
        this.promotions = promotions;
    }
}
