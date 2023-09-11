package com.hiufestainfo.domain.pub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
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
