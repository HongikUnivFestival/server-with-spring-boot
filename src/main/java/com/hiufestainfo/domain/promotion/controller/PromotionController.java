package com.hiufestainfo.domain.promotion.controller;

import com.hiufestainfo.domain.promotion.dto.PromotionDto;
import com.hiufestainfo.domain.promotion.dto.PromotionResponseDto;
import com.hiufestainfo.domain.promotion.service.PromotionService;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.global.response.SuccessResponse;
import com.hiufestainfo.global.utils.AuthentiatedUserUtils;
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
    private final AuthentiatedUserUtils authentiatedUserUtils;

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> createPromotion(@RequestBody PromotionDto promotionDto) {
        promotionService.createPromotion(promotionDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(message));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<PromotionResponseDto>> getAllPromotion() {
        User user = authentiatedUserUtils.getCurrentUser(); // 유저 정보 가져오기

        PromotionResponseDto promotionResponse =promotionService.getAllPromotions(user);

        return ResponseEntity.ok(SuccessResponse.of(promotionResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<PromotionDto>> getPromotion(@PathVariable Long id) {
        PromotionDto promotion = promotionService.getPromotion(id);
        return ResponseEntity.ok(SuccessResponse.of(promotion));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> updatePromotion(@PathVariable Long id, @RequestBody PromotionDto promotionDto) {
        promotionService.updatePromotion(id, promotionDto);
        return ResponseEntity.ok(SuccessResponse.empty());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.ok(SuccessResponse.empty());
    }
}
