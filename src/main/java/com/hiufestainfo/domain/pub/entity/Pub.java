package com.hiufestainfo.domain.pub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pub_id")
    private Long id;

    private String major;


    private String department;

    private String intro;
    private String menu;

    private String section;
    private String pubNum;
    public void updatePub(String major, String intro,String menu,String section,String pubNum,String department){
        this.major =major;
        this.intro = intro;
        this.menu = menu;
        this.section=section;
        this.pubNum=pubNum;
        this.department=department;
    }



}