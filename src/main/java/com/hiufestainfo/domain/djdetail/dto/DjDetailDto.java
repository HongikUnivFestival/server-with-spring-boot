package com.hiufestainfo.domain.djdetail.dto;

import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DjDetailDto {
    private String djName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String introduction;
    private String imageUrl;


    public static DjDetailDto fromEntity(DjDetail djDetail) {
        return DjDetailDto.builder()
                .djName(djDetail.getDjName())
                .startTime(djDetail.getStartTime())
                .endTime(djDetail.getEndTime())
                .introduction(djDetail.getIntroduction())
                .imageUrl(djDetail.getImageUrl())
                .build();
    }

    public DjDetail toEntity() {
        return DjDetail.builder()
                .djName(this.djName)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .introduction(this.introduction)
                .imageUrl(this.imageUrl)
                .build();
    }
}
