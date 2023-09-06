package com.hiufestainfo.domain.djdetail.service;


import com.hiufestainfo.domain.djdetail.dto.DjDetailDto;
import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import com.hiufestainfo.domain.djdetail.exception.CreateDjDetailBadRequestException;
import com.hiufestainfo.domain.djdetail.exception.DjDetailNotFoundException;
import com.hiufestainfo.domain.djdetail.exception.DjDetailServiceException;
import com.hiufestainfo.domain.djdetail.repository.DjDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
        DjDetail newDjDetail = djDetailDto.toEntity();
        try {
            djDetailRepository.save(newDjDetail);
        } catch (DataAccessException ex) {
            throw new CreateDjDetailBadRequestException();
        }
    }

    public DjDetailDto getDjDetail(Long id) {
        DjDetail djDetail = djDetailRepository.findById(id)
                .orElseThrow(() -> new DjDetailNotFoundException());
        return DjDetailDto.fromEntity(djDetail);
    }

    public List<DjDetailDto> getAllDjDetails() {
        try {
            List<DjDetail> djDetails = djDetailRepository.findAll();
            if (djDetails.isEmpty()) {
                throw new DjDetailNotFoundException();
            }
            return djDetails.stream()
                    .map(DjDetailDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            throw new DjDetailServiceException();
        }
    }

    public void updateDjDetail(Long id, DjDetailDto updatedDjDetailDto) {
        DjDetail djDetailToUpdate = djDetailRepository.findById(id)
                .orElseThrow(() -> new DjDetailNotFoundException());

        djDetailToUpdate = updatedDjDetailDto.toEntity().toBuilder()
                .id(id)
                .build();

        try {
            djDetailRepository.save(djDetailToUpdate);
        } catch (DataAccessException ex) {
            throw new DjDetailServiceException();
        }
    }

    public void deleteDjDetail(Long id) {
        if (!djDetailRepository.existsById(id)) {
            throw new DjDetailNotFoundException();
        }
        try {
            djDetailRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new DjDetailServiceException();
        }
    }
}

