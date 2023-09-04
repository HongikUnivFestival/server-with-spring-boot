package com.hiufestainfo.domain.promotion.controller;

import com.hiufestainfo.domain.promotion.dto.PromotionDto;
import com.hiufestainfo.domain.promotion.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<String> createPromotion(@RequestBody PromotionDto promotionDto) {
        promotionService.createPromotion(promotionDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<PromotionDto>> getAllPromotions() {
        List<PromotionDto> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> getPromotion(@PathVariable Long id) {
        PromotionDto promotion = promotionService.getPromotion(id);
        return ResponseEntity.ok(promotion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePromotion(@PathVariable Long id, @RequestBody PromotionDto promotionDto) {
        promotionService.updatePromotion(id, promotionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.ok().build();
    }
}

