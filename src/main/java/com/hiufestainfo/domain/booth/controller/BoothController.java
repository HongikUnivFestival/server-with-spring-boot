package com.hiufestainfo.domain.booth.controller;

import com.hiufestainfo.domain.booth.dto.BoothRequestDto;
import com.hiufestainfo.domain.booth.entity.Booth;
import com.hiufestainfo.domain.booth.service.BoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoothController {

    private final BoothService boothService;


    @PostMapping("/booth")
    public ResponseEntity<Booth> createBooth(@RequestBody BoothRequestDto requestDto) {
        Booth createdBooth = boothService.createBooth(requestDto);
        return ResponseEntity.ok(createdBooth);
    }
    @GetMapping("/booths")
    public List<Booth> getAllBooths(){
        return boothService.getAllBooths();
    }

    @PatchMapping("/booth/{boothId}")
    public ResponseEntity<Booth> modifyBooth(@PathVariable Long boothId, @RequestBody BoothRequestDto requestDto) {
        Booth modifiedBooth = boothService.updateBooth(boothId, requestDto);
        return ResponseEntity.ok(modifiedBooth);
    }

    @DeleteMapping("/booth/{boothId}")
    public ResponseEntity<String> deleteBooth(@PathVariable Long boothId){
        String result = boothService.deleteBooth(boothId);
        return ResponseEntity.ok(result);

    }


}