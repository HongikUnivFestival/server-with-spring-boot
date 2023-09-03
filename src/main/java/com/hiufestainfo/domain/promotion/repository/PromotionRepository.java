package com.hiufestainfo.domain.promotion.repository;

import com.hiufestainfo.domain.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
