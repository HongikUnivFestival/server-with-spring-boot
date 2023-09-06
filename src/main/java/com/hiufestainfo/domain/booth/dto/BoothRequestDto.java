package com.hiufestainfo.domain.booth.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Builder
@Getter
public class BoothRequestDto {
    private String boothNum;

    private String boothName;

    private String intro;

    private String host;
}