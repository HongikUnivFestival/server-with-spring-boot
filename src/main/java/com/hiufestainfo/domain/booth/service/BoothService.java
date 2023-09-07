package com.hiufestainfo.domain.booth.service;

import com.hiufestainfo.domain.booth.dto.BoothRequestDto;
import com.hiufestainfo.domain.booth.dto.BoothResponseDto;
import com.hiufestainfo.domain.booth.entity.Booth;
import com.hiufestainfo.domain.booth.exception.BoothNotFoundException;
import com.hiufestainfo.domain.booth.exception.CreateBoothBadRequestException;
import com.hiufestainfo.domain.booth.repository.BoothRepository;
import com.hiufestainfo.domain.user.entity.Role;
import com.hiufestainfo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoothService {
    private final BoothRepository boothRepository;
    public String deleteBooth(Long boothId) {
        Optional<Booth> boothOptional = boothRepository.findById(boothId);

        if (boothOptional.isPresent()) {
            boothRepository.deleteById(boothId);
            return "Delete Success";
        } else {
            throw new BoothNotFoundException();
        }
    }

    public Booth createBooth(BoothRequestDto requestDto) {

        String boothNum = requestDto.getBoothNum();
        String intro = requestDto.getIntro();
        String boothName = requestDto.getBoothName();
        String host = requestDto.getHost();


        // Booth 엔티티 생성
        Booth newBooth = Booth.builder()
                .boothNum(boothNum)
                .boothName(boothName)
                .intro(intro)
                .host(host)
                .build();

        // 생성된 Pub 엔티티를 저장하고 반환
        return boothRepository.save(newBooth);

    }

    public Booth updateBooth(Long boothId, BoothRequestDto requestDto){
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(() -> new BoothNotFoundException());

        String intro= requestDto.getIntro();
        String boothName = requestDto.getBoothName();
        String boothNum = requestDto.getBoothNum();
        String host = requestDto.getHost();

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
        if(host==null||(host=="")){
           host=booth.getHost();
        }

        booth.updateBooth(boothNum,boothName,intro,host);

        // 수정된 엔티티를 저장하고 반환합니다.
        return boothRepository.save(booth);
    }


    public BoothResponseDto getAllBooths (User user){

        Boolean isAdmin = false;
        Role accountStatus = user.getAuthInfo().getRole();
        if( accountStatus.getValue() == "GUEST"){
            isAdmin = false;
        } else if(accountStatus.getValue() == "ADMIN"){
            isAdmin = true;
        }

        List<Booth> booths = boothRepository.findAll();
        // booths가 null이면 빈 리스트로 초기화
        if (booths == null) {
            booths = new ArrayList<>();
        }

        return new BoothResponseDto(isAdmin, booths);
    }

}
