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
    public SuccessResponse<Object> createDjDetail(@RequestBody DjDetailDto djDetailDto) {
        djDetailService.createDjDetail(djDetailDto);
        return SuccessResponse.empty();
    }

    @GetMapping
    public SuccessResponse<Object> getAllDjDetail() {
        User user = authentiatedUserUtils.getCurrentUser();

        DjDetailResponseDto djDetailResponse = djDetailService.getAllDjDetails(user);

        return SuccessResponse.of(djDetailResponse);
    }

    @PatchMapping("/{id}")
    public SuccessResponse<Object> updateDjDetail(@PathVariable Long id, @RequestBody DjDetailDto djDetailDto) {
        djDetailService.updateDjDetail(id, djDetailDto);
        return SuccessResponse.empty();
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<Object> deleteDjDetail(@PathVariable Long id) {
        djDetailService.deleteDjDetail(id);
        return SuccessResponse.empty();
    }
}

