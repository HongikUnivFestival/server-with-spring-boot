package com.hiufestainfo.domain.festival.service;


import com.hiufestainfo.domain.festival.dto.FestivalDto;
import com.hiufestainfo.domain.festival.entity.Festival;
import com.hiufestainfo.domain.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public void createFestival(FestivalDto festivalDto) {
        Festival newFestival = festivalDto.toEntity();
        festivalRepository.save(newFestival);
    }


    public FestivalDto getFestival(Long id) {
        Festival festival = festivalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));
        return FestivalDto.fromEntity(festival);
    }

    public List<FestivalDto> getAllFestivals() {
        List<Festival> festivals = festivalRepository.findAll();
        return festivals.stream()
                .map(FestivalDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void updateFestival(Long id, FestivalDto updatedFestivalDto) {
        Festival festivalToUpdate = festivalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));

        festivalToUpdate = updatedFestivalDto.toEntity().toBuilder()  // 업데이트할 필드를 DTO에서 가져와 엔티티로 복사
                .id(id)  // 기존 ID를 유지하여 업데이트 대상의 엔티티를 지정
                .build();

        festivalRepository.save(festivalToUpdate);
    }

    public void deleteFestival(Long id) {
        if (!festivalRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다.");
        }
        festivalRepository.deleteById(id);
    }
}