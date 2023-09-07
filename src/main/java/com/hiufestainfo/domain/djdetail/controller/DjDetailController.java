package com.hiufestainfo.domain.djdetail.controller;

import com.hiufestainfo.domain.djdetail.dto.DjDetailDto;
import com.hiufestainfo.domain.djdetail.service.DjDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festival/schedules")
@RequiredArgsConstructor
public class DjDetailController {
    private final DjDetailService djDetailService;

    @PostMapping
    public ResponseEntity<String> createDjDetail(@RequestBody DjDetailDto djDetailDto) {
        djDetailService.createDjDetail(djDetailDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<DjDetailDto>> getAllDjDetails() {
        List<DjDetailDto> djDetails = djDetailService.getAllDjDetails();
        return ResponseEntity.ok(djDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DjDetailDto> getDjDetail(@PathVariable Long id) {
        DjDetailDto djDetail = djDetailService.getDjDetail(id);
        return ResponseEntity.ok(djDetail);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateDjDetail(@PathVariable Long id, @RequestBody DjDetailDto djDetailDto) {
        djDetailService.updateDjDetail(id, djDetailDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDjDetail(@PathVariable Long id) {
        djDetailService.deleteDjDetail(id);
        return ResponseEntity.ok().build();
    }
}


