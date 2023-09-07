package com.hiufestainfo.domain.djdetail.controller;

import com.hiufestainfo.domain.djdetail.dto.DjDetailDto;
import com.hiufestainfo.domain.djdetail.dto.DjDetailResponseDto;
import com.hiufestainfo.domain.djdetail.service.DjDetailService;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.global.response.SuccessResponse;
import com.hiufestainfo.global.utils.AuthentiatedUserUtils;
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
    private final AuthentiatedUserUtils authentiatedUserUtils;

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> createDjDetail(@RequestBody DjDetailDto djDetailDto) {
        djDetailService.createDjDetail(djDetailDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(message));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<DjDetailResponseDto>> getAllDjDetail() {
        User user = authentiatedUserUtils.getCurrentUser(); // 유저 정보 가져오기

        DjDetailResponseDto djDetailResponse = djDetailService.getAllDjDetails(user);

        return ResponseEntity.ok(SuccessResponse.of(djDetailResponse));
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

