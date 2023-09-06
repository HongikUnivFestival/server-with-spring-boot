package com.hiufestainfo.domain.festival.service;


import com.hiufestainfo.domain.festival.dto.FestivalDto;
import com.hiufestainfo.domain.festival.entity.Festival;
import com.hiufestainfo.domain.festival.exception.CreateFestivalBadRequestException;
import com.hiufestainfo.domain.festival.exception.FestivalNotFoundException;
import com.hiufestainfo.domain.festival.exception.FestivalServiceException;
import com.hiufestainfo.domain.festival.repository.FestivalRepository;
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
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public void createFestival(FestivalDto festivalDto) {
        Festival newFestival = festivalDto.toEntity();
        try {
            festivalRepository.save(newFestival);
        } catch (DataAccessException ex) {
            throw new CreateFestivalBadRequestException();
        }
    }

    public FestivalDto getFestival(Long id) {
        Festival festival = festivalRepository.findById(id)
                .orElseThrow(() -> new FestivalNotFoundException());
        return FestivalDto.fromEntity(festival);
    }

    public void updateFestival(Long id, FestivalDto updatedFestivalDto) {
        Festival festivalToUpdate = festivalRepository.findById(id)
                .orElseThrow(() -> new FestivalNotFoundException());

        festivalToUpdate = updatedFestivalDto.toEntity().toBuilder()
                .id(id)
                .build();

        try {
            festivalRepository.save(festivalToUpdate);
        } catch (DataAccessException ex) {
            throw new FestivalServiceException();
        }
    }

    public void deleteFestival(Long id) {
        if (!festivalRepository.existsById(id)) {
            throw new FestivalNotFoundException();
        }
        try {
            festivalRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new FestivalServiceException();
        }
    }
}