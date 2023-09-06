package com.hiufestainfo.domain.djdetail.controller;

import com.hiufestainfo.domain.djdetail.dto.DjDetailDto;
import com.hiufestainfo.domain.djdetail.service.DjDetailService;
import com.hiufestainfo.global.response.SuccessResponse;
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
    public ResponseEntity<SuccessResponse<String>> createDjDetail(@RequestBody DjDetailDto djDetailDto) {
        djDetailService.createDjDetail(djDetailDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(message));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DjDetailDto>>> getAllDjDetail() {
        List<DjDetailDto> djDetails = djDetailService.getAllDjDetails();
        return ResponseEntity.ok(SuccessResponse.of(djDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<DjDetailDto>> getDjDetail(@PathVariable Long id) {
        DjDetailDto djDetail = djDetailService.getDjDetail(id);
        return ResponseEntity.ok(SuccessResponse.of(djDetail));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> updateDjDetail(@PathVariable Long id, @RequestBody DjDetailDto djDetailDto) {
        djDetailService.updateDjDetail(id, djDetailDto);
        return ResponseEntity.ok(SuccessResponse.empty());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteDjDetail(@PathVariable Long id) {
        djDetailService.deleteDjDetail(id);
        return ResponseEntity.ok(SuccessResponse.empty());
    }
}

