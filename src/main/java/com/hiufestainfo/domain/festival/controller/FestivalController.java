package com.hiufestainfo.domain.festival.controller;

import com.hiufestainfo.domain.festival.dto.FestivalDto;
import com.hiufestainfo.domain.festival.dto.FestivalResponseDto;
import com.hiufestainfo.domain.festival.service.FestivalService;
import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.global.response.SuccessResponse;
import com.hiufestainfo.global.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festival")
@RequiredArgsConstructor
public class FestivalController {
    private final FestivalService festivalService;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> createFestival(@RequestBody FestivalDto festivalDto) {
        festivalService.createFestival(festivalDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(message));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<FestivalDto>> getFestival() {
        Long defaultId = 1L; // 항상 1인 id를 사용
        User user = authentiatedUserUtils.getCurrentUser();
        FestivalResponseDto festivalResponse = festivalService.getFestival(defaultId,user);
        return ResponseEntity.ok(SuccessResponse.of(festivalResponse));
    }

    @PatchMapping
    public ResponseEntity<SuccessResponse<Void>> updateFestival(@RequestBody FestivalDto festivalDto) {
        Long defaultId = 1L; // 항상 1인 id를 사용
        festivalService.updateFestival(defaultId, festivalDto);
        return ResponseEntity.ok(SuccessResponse.empty());
    }

    @DeleteMapping
    public ResponseEntity<SuccessResponse<Void>> deleteFestival() {
        Long defaultId = 1L; // 항상 1인 id를 사용
        festivalService.deleteFestival(defaultId);
        return ResponseEntity.ok(SuccessResponse.empty());
    }
}
