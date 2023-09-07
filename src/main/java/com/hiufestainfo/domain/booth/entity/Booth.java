package com.hiufestainfo.domain.booth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booth_id")
    private Long id;

    private String boothNum;

    private String boothName;

    private String intro;
    @Nullable
    private String imageUrl;

    public void updateBooth(String boothNum,String boothName, String intro,String imageUrl){
        this.boothNum = boothNum;
        this.boothName =boothName;
        this.intro = intro;
        this.imageUrl=imageUrl;
    }

}
