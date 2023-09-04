package com.hiufestainfo.domain.festival.dto;

import com.hiufestainfo.domain.festival.entity.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FestivalDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer entranceFee;
    private String precautions;
    private String introduction;

    public static FestivalDto fromEntity(Festival festival) {
        return FestivalDto.builder()
                .startTime(festival.getStartTime())
                .endTime(festival.getEndTime())
                .entranceFee(festival.getEntranceFee())
                .precautions(festival.getPrecautions())
                .introduction(festival.getIntroduction())
                .build();
    }

    public Festival toEntity() {
        return Festival.builder()
                .startTime(this.startTime)
                .endTime(this.endTime)
                .entranceFee(this.entranceFee)
                .precautions(this.precautions)
                .introduction(this.introduction)
                .build();
    }
}
