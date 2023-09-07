package com.hiufestainfo.domain.festival.dto;

import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import com.hiufestainfo.domain.festival.entity.Festival;
import lombok.Getter;

import java.util.List;

@Getter
public class FestivalResponseDto {
    private Boolean isAdmin;
    private Festival festival;
    public FestivalResponseDto(Boolean isAdmin, Festival festival) {
        this.isAdmin = isAdmin;
        this.festival = festival;
    }
}

