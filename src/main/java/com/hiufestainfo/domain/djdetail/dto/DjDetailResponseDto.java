package com.hiufestainfo.domain.djdetail.dto;

import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class DjDetailResponseDto {
    private Boolean isAdmin;
    private List<DjDetail> djDetails;

    public DjDetailResponseDto(Boolean isAdmin, List<DjDetail> djDetails) {
        this.isAdmin = isAdmin;
        this.djDetails = djDetails;
    }
}
