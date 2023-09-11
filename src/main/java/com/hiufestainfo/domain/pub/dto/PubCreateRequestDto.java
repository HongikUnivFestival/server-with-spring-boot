package com.hiufestainfo.domain.pub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;


@Getter
@RequiredArgsConstructor
public class PubCreateRequestDto {
    private String major;

    private String department;

    @Nullable
    private String imageUrl;

    private String intro;
    private String menu;
    private String section;
}
