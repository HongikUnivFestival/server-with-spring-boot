package com.hiufestainfo.domain.pub.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Builder
@Getter
public class PubRequestDto {
    private String major;

    private String department;

    private String intro;
    private String menu;
    private String section;
    private String pubNum;
    @Nullable
    private String imageUrl;
}
