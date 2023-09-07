package com.hiufestainfo.domain.djdetail.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@Table(name = "dj_detail")
public class DjDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String djName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String introduction;
    private String imageUrl;

    protected DjDetail() {
    }

}
