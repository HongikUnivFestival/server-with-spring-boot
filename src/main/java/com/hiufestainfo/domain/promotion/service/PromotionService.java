package com.hiufestainfo.domain.promotion.service;

import com.hiufestainfo.domain.promotion.dto.PromotionDto;
import com.hiufestainfo.domain.promotion.entity.Promotion;
import com.hiufestainfo.domain.promotion.exception.CreatePromotionBadRequestException;
import com.hiufestainfo.domain.promotion.exception.PromotionNotFoundException;
import com.hiufestainfo.domain.promotion.exception.PromotionServiceException;
import com.hiufestainfo.domain.promotion.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public void createPromotion(PromotionDto promotionDto) {
        Promotion newPromotion = promotionDto.toEntity();
        try {
            promotionRepository.save(newPromotion);
        } catch (DataAccessException ex) {
            throw new CreatePromotionBadRequestException();
        }
    }

    public PromotionDto getPromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException());
        return PromotionDto.fromEntity(promotion);
    }

    public List<PromotionDto> getAllPromotions() {
        try {
            List<Promotion> promotions = promotionRepository.findAll();
            if (promotions.isEmpty()) {
                throw new PromotionNotFoundException();
            }
            return promotions.stream()
                    .map(PromotionDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            throw new PromotionServiceException();
        }
    }

    public void updatePromotion(Long id, PromotionDto updatedPromotionDto) {
        Promotion promotionToUpdate = promotionRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException());

        promotionToUpdate = updatedPromotionDto.toEntity().toBuilder()
                .id(id)
                .build();

        try {
            promotionRepository.save(promotionToUpdate);
        } catch (DataAccessException ex) {
            throw new PromotionServiceException();
        }
    }

    public void deletePromotion(Long id) {
        if (!promotionRepository.existsById(id)) {
            throw new PromotionNotFoundException();
        }
        try {
            promotionRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new PromotionServiceException();
        }
    }
}
