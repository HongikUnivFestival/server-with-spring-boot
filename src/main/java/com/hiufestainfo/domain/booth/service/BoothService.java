package com.hiufestainfo.domain.booth.service;

import com.hiufestainfo.domain.booth.dto.BoothRequestDto;
import com.hiufestainfo.domain.booth.entity.Booth;
import com.hiufestainfo.domain.booth.exception.BoothNotFoundException;
import com.hiufestainfo.domain.booth.exception.CreateBoothBadRequestException;
import com.hiufestainfo.domain.booth.repository.BoothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoothService {
    private final BoothRepository boothRepository;
    public String deleteBooth(Long boothId) {
        boothRepository.deleteById(boothId);
        return "Delete Success";
    }

    public Booth createBooth(BoothRequestDto requestDto) {

        String boothNum = requestDto.getBoothNum();
        String intro = requestDto.getIntro();
        String boothName = requestDto.getBoothName();


        // Booth 엔티티 생성
        Booth newBooth = Booth.builder()
                .boothNum(boothNum)
                .boothName(boothName)
                .intro(intro)
                .build();

        // 생성된 Pub 엔티티를 저장하고 반환
        return boothRepository.save(newBooth);

    }

    public Booth updateBooth(Long boothId, BoothRequestDto requestDto){
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pub ID: " + boothId));

        String intro= requestDto.getIntro();
        String boothName = requestDto.getBoothName();
        String boothNum = requestDto.getBoothNum();

        // 요청된 데이터로 엔티티를 수정합니다.


        if(boothNum==null || (boothNum=="")){
            boothNum = booth.getBoothNum();
        }
        if(intro==null||(intro=="")){
            intro = booth.getIntro();

        }
        if(boothName==null||(boothName=="")){
            boothName = booth.getBoothName();
        }

        booth.updateBooth(boothNum,boothName,intro);

        // 수정된 엔티티를 저장하고 반환합니다.
        return boothRepository.save(booth);
    }


    public List<Booth> getAllBooths (){
        List<Booth> booths = boothRepository.findAll();
        if (booths == null || booths.isEmpty()) {
            throw new BoothNotFoundException();
        }
        return booths;
    }

}
