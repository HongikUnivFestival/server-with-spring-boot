package com.hiufestainfo.domain.pub.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PubRequestDto {
    private String major;

    private String department;

    private String intro;
    private String menu;
    private String section;
    private String pubNum;
}
