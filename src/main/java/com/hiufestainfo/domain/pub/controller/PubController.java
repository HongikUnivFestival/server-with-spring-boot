package com.hiufestainfo.domain.pub.controller;

import com.hiufestainfo.domain.pub.dto.PubRequestDto;
import com.hiufestainfo.domain.pub.entity.Pub;
import com.hiufestainfo.domain.pub.service.PubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PubController {
    private final PubService pubService;


    //ok
    @PostMapping("/pubs")
    public ResponseEntity<Pub> createPub(@RequestBody PubRequestDto requestDto) {
        Pub createdPub = pubService.createPub(requestDto);
        return ResponseEntity.ok(createdPub);
    }


    @PatchMapping("/pubs/{pubId}")
    public ResponseEntity<Pub> modifyPub(@PathVariable Long pubId, @RequestBody PubRequestDto requestDto) {
        Pub modifiedPub = pubService.updatePub(pubId, requestDto);
        return ResponseEntity.ok(modifiedPub);
    }


    //ok
    @GetMapping("/pubs")
    public List<Pub> getAllPubs(){
        return pubService.getAllPubs();
    }

    @DeleteMapping("/pubs/{pubId}")
    public ResponseEntity<String> deletePub(@PathVariable Long pubId) {
        String result=pubService.deletePub(pubId);
        return ResponseEntity.ok(result);
    }

    //ok
    @GetMapping("/pubs/{department}")
    public List<Pub> getPubsByDepartment(@PathVariable String department) {

        return pubService.getPubByDepartment(department);
    }
}
