package com.hiufestainfo.domain.festival.service;


import com.hiufestainfo.domain.festival.dto.FestivalDto;
import com.hiufestainfo.domain.festival.dto.FestivalResponseDto;
import com.hiufestainfo.domain.festival.entity.Festival;
import com.hiufestainfo.domain.festival.exception.CreateFestivalBadRequestException;
import com.hiufestainfo.domain.festival.exception.FestivalNotFoundException;
import com.hiufestainfo.domain.festival.exception.FestivalServiceException;
import com.hiufestainfo.domain.festival.repository.FestivalRepository;
import com.hiufestainfo.domain.user.entity.Role;
import com.hiufestainfo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public FestivalResponseDto getFestival(Long id, User user) {
        Boolean isAdmin = false;
        Role accountStatus = user.getAuthInfo().getRole();

        // "GUEST" 또는 "ADMIN"인지 확인하여 isAdmin 설정
        if ("GUEST".equals(accountStatus.getValue())) {
            isAdmin = false;
        } else if ("ADMIN".equals(accountStatus.getValue())) {
            isAdmin = true;
        }


        Long festivalId = 1L;
        Optional<Festival> optionalFestival = festivalRepository.findById(festivalId);

        if (!optionalFestival.isPresent()) {
            throw new FestivalNotFoundException();
        }

        Festival festival = optionalFestival.get();

        return new FestivalResponseDto(isAdmin, festival);
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