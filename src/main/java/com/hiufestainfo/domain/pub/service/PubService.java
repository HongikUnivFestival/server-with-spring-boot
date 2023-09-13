package com.hiufestainfo.domain.pub.service;

import com.hiufestainfo.domain.pub.dto.PubCreateRequestDto;
import com.hiufestainfo.domain.pub.dto.PubRequestDto;
import com.hiufestainfo.domain.pub.dto.PubResponseDto;
import com.hiufestainfo.domain.pub.entity.Pub;
import com.hiufestainfo.domain.pub.excpeption.DepartmentNotFoundException;
import com.hiufestainfo.domain.pub.excpeption.PubNotFoundException;
import com.hiufestainfo.domain.pub.repository.PubRepository;
import com.hiufestainfo.domain.user.entity.Role;
import com.hiufestainfo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PubService {

    private final PubRepository pubRepository;  // PubRepository에 해당하는 필드를 선언하세요.

    public Pub createPub(PubCreateRequestDto requestDto) {
        String department = requestDto.getDepartment();
        String major = requestDto.getMajor();
        String intro = requestDto.getIntro();
        String menu = requestDto.getMenu();
        String section = requestDto.getSection();
        String imageUrl = requestDto.getImageUrl();
        //findDepartment해서 해당 department없으면 DEPARTMENT NOT FOUND에러 호출하고 저장 x
        findDepartment(department);

        // Pub 엔티티 생성을 위한 Builder를 먼저 초기화합니다.
        Pub.PubBuilder pubBuilder = Pub.builder()
                .major(major)
                .department(department) // 찾은 부서로 설정
                .intro(intro)
                .menu(menu)
                .section(section);

        if (imageUrl != null) {
            pubBuilder.imageUrl(imageUrl);
        }

        // Pub 엔티티 생성
        Pub newPub = pubBuilder.build();
        // Pub 엔티티 생성


        // 생성된 Pub 엔티티를 저장하고 반환
        return pubRepository.save(newPub);
    }

    public Pub updatePub(Long pubId, PubRequestDto requestDto) {
        // pubId를 이용하여 업데이트할 Pub 엔티티를 조회합니다.
        Pub pub = pubRepository.findById(pubId)
                .orElseThrow(() -> new PubNotFoundException());
        String major = requestDto.getMajor();
        String department = requestDto.getDepartment();
        String intro= requestDto.getIntro();
        String menu = requestDto.getMenu();
        String section= requestDto.getSection();
        String pubNum= requestDto.getPubNum();
        String imageUrl=requestDto.getImageUrl();

        //department에러 로직 추가하기
        findDepartment(department);

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
        if(imageUrl== null||(imageUrl=="")){
            imageUrl=pub.getImageUrl();
        }
        pub.updatePub(major,intro,menu,section,pubNum,department,imageUrl);

        // 수정된 엔티티를 저장하고 반환합니다.
        return pubRepository.save(pub);
    }


    public String deletePub(Long pubId) {
        Optional<Pub> pubOptional = pubRepository.findById(pubId);

        if (pubOptional.isPresent()) {
            pubRepository.deleteById(pubId);
            return "Delete Success";
        } else {
            throw new PubNotFoundException(); // Assuming you have a PubNotFoundException class
        }
    }

    public PubResponseDto getPubByDepartment(String department,User user){
        //Department enumDepartment = Department.valueOf(department); // Convert department name to enum
        //System.out.println(enumDepartment);
        Boolean isAdmin = false;
        List<Pub> pubs;
        Role accountStatus = user.getAuthInfo().getRole();
        if( accountStatus.getValue() == "GUEST"){
            isAdmin = false;
        } else if(accountStatus.getValue() == "ADMIN"){
            isAdmin = true;
        }

        if (department.equals("all")){
            pubs =getAllPubs();
        }else{
            String korDepartment= convertDepartment(department);
            pubs  = pubRepository.findByDepartment(korDepartment);
        }
        // Use the repository to find pubs by department

        if(pubs==null){
            pubs = new ArrayList<>();
        }
        return new PubResponseDto(isAdmin,pubs);
    }

    public List<Pub> getAllPubs (){
        List<Pub> pubs = pubRepository.findAll();
        if (pubs == null) {
           pubs = new ArrayList<>();
        }
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
            case "architecture":
                return "건축";
            case "designBusiness":
                return "디자인경영융합";

            default:
                throw new DepartmentNotFoundException();
        }
    }

    private Boolean findDepartment(String department){
        switch (department){
            case "자전":
                return true;
            case "경영":
                return true;
            case "미대":
                return true;
            case "경제":
                return true;
            case "공연예술":
                return true;
            case "사범대":
                return true;
            case "법대":
                return true;
            case "문대":
                return true;
            case "공대":
                return true;
            case "총동아리 연합":
                return true;
            case "건축":
                return true;
            case "디자인경영융합":
                return true;
            default:
                throw new DepartmentNotFoundException();
        }
    }
}