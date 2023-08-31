package com.hiufestainfo.domain.booth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoothRequestDto {
    private String boothNum;

    private String boothName;

    private String intro;
}