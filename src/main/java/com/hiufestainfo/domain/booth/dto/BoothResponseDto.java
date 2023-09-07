package com.hiufestainfo.domain.booth.dto;

import com.hiufestainfo.domain.booth.entity.Booth;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BoothResponseDto {
    private Boolean isAdmin;

    private List<Booth> booth;
    public BoothResponseDto(Boolean isAdmin, List<Booth> booth) {
        this.isAdmin = isAdmin;
        this.booth = booth;
    }
}
