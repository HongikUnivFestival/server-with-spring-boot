package com.hiufestainfo.domain.djdetail.service;


import com.hiufestainfo.domain.djdetail.dto.DjDetailDto;
import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import com.hiufestainfo.domain.djdetail.repository.DjDetailRepository;
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
public class DjDetailService {
    private final DjDetailRepository djDetailRepository;

    public void createDjDetail(DjDetailDto djDetailDto) {
        DjDetail newFestDetail = djDetailDto.toEntity();
        djDetailRepository.save(newFestDetail);
    }

    public DjDetailDto getDjDetail(Long id) {
        DjDetail djDetail = djDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));
        return DjDetailDto.fromEntity(djDetail);
    }

    public List<DjDetailDto> getAllDjDetails() {
        List<DjDetail> djDetails = djDetailRepository.findAll();
        return djDetails.stream()
                .map(DjDetailDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void updateDjDetail(Long id, DjDetailDto updatedDjDetailDto) {
        DjDetail djDetailToUpdate = djDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."));

        djDetailToUpdate = updatedDjDetailDto.toEntity().toBuilder()
                .id(id)
                .build();

        djDetailRepository.save(djDetailToUpdate);
    }

    public void deleteDjDetail(Long id) {
        if (!djDetailRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다.");
        }
        djDetailRepository.deleteById(id);
    }
}

