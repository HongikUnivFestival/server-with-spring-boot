package com.hiufestainfo.domain.promotion.dto;

import com.hiufestainfo.domain.promotion.entity.Promotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PromotionDto {
    private String name;
    private String introduction;

    private String booth_num;

    public static PromotionDto fromEntity(Promotion promotion) {
        return PromotionDto.builder()
                .name(promotion.getName())
                .introduction(promotion.getIntroduction())
                .booth_num(promotion.getBooth_num())
                .build();
    }

    public Promotion toEntity() {
        return Promotion.builder()
                .name(this.name)
                .introduction(this.introduction)
                .booth_num(this.booth_num)
                .build();
    }
}

