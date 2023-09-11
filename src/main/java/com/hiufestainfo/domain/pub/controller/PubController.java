package com.hiufestainfo.domain.pub.controller;

import com.hiufestainfo.domain.pub.dto.PubCreateRequestDto;
import com.hiufestainfo.domain.pub.dto.PubRequestDto;
import com.hiufestainfo.domain.pub.dto.PubResponseDto;
import com.hiufestainfo.domain.pub.entity.Pub;
import com.hiufestainfo.domain.pub.service.PubService;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.global.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PubController {
    private final PubService pubService;
    private final AuthentiatedUserUtils authentiatedUserUtils;


    //ok
    @PostMapping("/pubs")
    public ResponseEntity<Pub> createPub(@RequestBody PubCreateRequestDto requestDto) {
        Pub createdPub = pubService.createPub(requestDto);
        //department에러 로직 추가

        return ResponseEntity.ok(createdPub);
    }


    @PatchMapping("/pubs/{pubId}")
    public ResponseEntity<Pub> modifyPub(@PathVariable Long pubId, @RequestBody PubRequestDto requestDto) {
        Pub modifiedPub = pubService.updatePub(pubId, requestDto);
        return ResponseEntity.ok(modifiedPub);
    }


    //ok
//    @GetMapping("/pubs")
//    public PubResponseDto getAllPubs(){
//        User user = authentiatedUserUtils.getCurrentUser();
//        System.out.println(user.getId());
//        return pubService.getAllPubs(user);
//    }

    @DeleteMapping("/pubs/{pubId}")
    public ResponseEntity<String> deletePub(@PathVariable Long pubId) {
        String result=pubService.deletePub(pubId);
        return ResponseEntity.ok(result);
    }

    //ok
    @GetMapping("/pubs/{department}")
    public PubResponseDto getPubsByDepartment(@PathVariable String department) {
        User user = authentiatedUserUtils.getCurrentUser();
        return pubService.getPubByDepartment(department,user);
    }
}
