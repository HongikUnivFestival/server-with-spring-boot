package com.hiufestainfo.domain.pub.dto;

import com.hiufestainfo.domain.pub.entity.Pub;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PubResponseDto {
    private Boolean isAdmin;
    private List<Pub> pub;
    public PubResponseDto(Boolean isAdmin, List<Pub> pub){
        this.isAdmin=isAdmin;
        this.pub=pub;
    }
}
