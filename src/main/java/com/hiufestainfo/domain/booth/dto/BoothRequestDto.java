package com.hiufestainfo.domain.booth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public class BoothRequestDto {
    private String boothNum;

    private String boothName;

    private String intro;

    private String host;
}