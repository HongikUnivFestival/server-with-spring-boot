package com.hiufestainfo.domain.promotion.service;

import com.hiufestainfo.domain.promotion.dto.PromotionDto;
import com.hiufestainfo.domain.promotion.entity.Promotion;
import com.hiufestainfo.domain.promotion.repository.PromotionRepository;
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
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public void createPromotion(PromotionDto promotionDto) {
        Promotion newPromotion = promotionDto.toEntity();
        promotionRepository.save(newPromotion);
    }


    public PromotionDto getPromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));
        return PromotionDto.fromEntity(promotion);
    }

    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream()
                .map(PromotionDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void updatePromotion(Long id, PromotionDto updatedPromotionDto) {
        Promotion promotionToUpdate = promotionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));

        promotionToUpdate = updatedPromotionDto.toEntity().toBuilder()  // 업데이트할 필드를 DTO에서 가져와 엔티티로 복사
                .id(id)  // 기존 ID를 유지하여 업데이트 대상의 엔티티를 지정
                .build();

        promotionRepository.save(promotionToUpdate);
    }

    public void deletePromotion(Long id) {
        if (!promotionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다.");
        }
        promotionRepository.deleteById(id);
    }

}
