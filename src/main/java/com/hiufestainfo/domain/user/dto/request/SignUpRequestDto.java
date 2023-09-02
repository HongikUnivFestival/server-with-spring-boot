package com.hiufestainfo.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String universityName;
    @NotNull
    private String major;
}
