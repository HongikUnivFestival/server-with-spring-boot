package com.hiufestainfo.domain.festival.controller;

import com.hiufestainfo.domain.festival.dto.FestivalDto;
import com.hiufestainfo.domain.festival.service.FestivalService;
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
    @PostMapping
    public ResponseEntity<String> createFestival(@RequestBody FestivalDto festivalDto) {
        festivalService.createFestival(festivalDto);
        String message = "Successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<FestivalDto>> getAllFestivals() {
        List<FestivalDto> festivals = festivalService.getAllFestivals();
        return ResponseEntity.ok(festivals);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFestival(@PathVariable Long id, @RequestBody FestivalDto festivalDto) {
        festivalService.updateFestival(id, festivalDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFestival(@PathVariable Long id) {
        festivalService.deleteFestival(id);
        return ResponseEntity.ok().build();
    }
}
