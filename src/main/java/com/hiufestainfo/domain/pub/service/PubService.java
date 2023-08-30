package com.hiufestainfo.domain.pub.service;

import com.hiufestainfo.domain.pub.dto.PubRequestDto;
import com.hiufestainfo.domain.pub.entity.Pub;
import com.hiufestainfo.domain.pub.repository.PubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PubService {

    private final PubRepository pubRepository;  // PubRepository에 해당하는 필드를 선언하세요.

    public Pub createPub(PubRequestDto requestDto) {
        String department = requestDto.getDepartment();
        String major = requestDto.getMajor();
        String intro = requestDto.getIntro();
        String menu = requestDto.getMenu();
        String section = requestDto.getSection();
        String pubNum = requestDto.getPubNum();

        // Pub 엔티티 생성
        Pub newPub = Pub.builder()
                .major(major)
                .department(department)
                .intro(intro)
                .menu(menu)
                .section(section)
                .pubNum(pubNum)
                .build();

        // 생성된 Pub 엔티티를 저장하고 반환
        return pubRepository.save(newPub);
    }

    public Pub updatePub(Long pubId, PubRequestDto requestDto) {
        // pubId를 이용하여 업데이트할 Pub 엔티티를 조회합니다.
        Pub pub = pubRepository.findById(pubId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pub ID: " + pubId));
        String major = requestDto.getMajor();
        String department = requestDto.getDepartment();
        String intro= requestDto.getIntro();
        String menu = requestDto.getMenu();
        String section= requestDto.getSection();
        String pubNum= requestDto.getPubNum();

        // 요청된 데이터로 엔티티를 수정합니다.


        if(major==null || (major=="")){
            major = pub.getMajor();
        }
        if(intro==null||(intro=="")){
            intro = pub.getIntro();

        }
        if(menu==null||(menu=="")){
            menu = pub.getMenu();
        }
        if(section==null||(section=="")){
            section = pub.getSection();
        }
        if(pubNum==null||(pubNum=="")){
            pubNum = pub.getPubNum();

        }
        if (department== null||(department=="")){
            department= pub.getDepartment();

        }
        pub.updatePub(major,intro,menu,section,pubNum,department);

        // 수정된 엔티티를 저장하고 반환합니다.
        return pubRepository.save(pub);
    }


    public String deletePub(Long pubId) {
        pubRepository.deleteById(pubId);
        return "Delete Success";
    }

    public List<Pub> getPubByDepartment(String department){
        //Department enumDepartment = Department.valueOf(department); // Convert department name to enum
        //System.out.println(enumDepartment);

        // Use the repository to find pubs by department
        String korDepartment= convertDepartment(department);
        List<Pub> pubs = pubRepository.findByDepartment(korDepartment);

        return pubs;
    }

    public List<Pub> getAllPubs (){
        List<Pub> pubs = pubRepository.findAll();
        return pubs;
    }



    private String convertDepartment(String departmentName) {
        switch (departmentName) {
            case "freeMajor":
                return "자전";
            case "business":
                return "경영";
            case "fineArt":
                return "미대";
            case "economics":
                return "경제";
            case "performingArts":
                return "공연예술";
            case "education":
                return "사범대";
            case "law":
                return "법대";
            case "humanities":
                return "문대";
            case "engineering":
                return "공대";
            case "union":
                return "총동아리 연합";
            default:
                throw new IllegalArgumentException("Invalid department name: " + departmentName);
        }
    }
}