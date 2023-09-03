package com.hiufestainfo.domain.djdetail.dto;

import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DjDetailDto {
    private String djName;
    private String introduction;
    private String imageUrl;


    public static DjDetailDto fromEntity(DjDetail djDetail) {
        return DjDetailDto.builder()
                .djName(djDetail.getDjName())
                .introduction(djDetail.getIntroduction())
                .imageUrl(djDetail.getImageUrl())
                .build();
    }

    public DjDetail toEntity() {
        return DjDetail.builder()
                .djName(this.djName)
                .introduction(this.introduction)
                .imageUrl(this.imageUrl)
                .build();
    }
}
