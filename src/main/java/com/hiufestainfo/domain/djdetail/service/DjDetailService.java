package com.hiufestainfo.domain.djdetail.service;


import com.hiufestainfo.domain.djdetail.dto.DjDetailDto;
import com.hiufestainfo.domain.djdetail.dto.DjDetailResponseDto;
import com.hiufestainfo.domain.djdetail.entity.DjDetail;
import com.hiufestainfo.domain.djdetail.exception.CreateDjDetailBadRequestException;
import com.hiufestainfo.domain.djdetail.exception.DjDetailNotFoundException;
import com.hiufestainfo.domain.djdetail.exception.DjDetailServiceException;
import com.hiufestainfo.domain.djdetail.repository.DjDetailRepository;
import com.hiufestainfo.domain.user.entity.Role;
import com.hiufestainfo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public DjDetailResponseDto getAllDjDetails(User user) {
        Boolean isAdmin = false;
        Role accountStatus = user.getAuthInfo().getRole();

        // "GUEST" 또는 "ADMIN"인지 확인하여 isAdmin 설정
        if ("GUEST".equals(accountStatus.getValue())) {
            isAdmin = false;
        } else if ("ADMIN".equals(accountStatus.getValue())) {
            isAdmin = true;
        }

        List<DjDetail> djDetails = djDetailRepository.findAll();

        // djDetails가 null이면 빈 리스트로 초기화
        if (djDetails == null) {
            djDetails = new ArrayList<>();
        }

        if (djDetails.isEmpty()) {
            throw new DjDetailNotFoundException();
        }

        return new DjDetailResponseDto(isAdmin, djDetails);

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

