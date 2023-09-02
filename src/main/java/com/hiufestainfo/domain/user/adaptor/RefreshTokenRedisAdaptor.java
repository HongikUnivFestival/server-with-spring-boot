package com.hiufestainfo.domain.user.adaptor;

import com.hiufestainfo.domain.user.entity.RefreshToken;
import com.hiufestainfo.domain.user.exception.ExpiredRefreshTokenException;
import com.hiufestainfo.domain.user.repository.RefreshTokenRedisDao;
import com.hiufestainfo.global.annotation.Adaptor;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RefreshTokenRedisAdaptor {
    private final RefreshTokenRedisDao refreshTokenRedisDao;

    public void save(Long id, RefreshToken refreshToken){
        refreshTokenRedisDao.save(id, refreshToken);
    }
    public RefreshToken findById(Long id){
        return refreshTokenRedisDao.findById(id)
                .orElseThrow(() -> new ExpiredRefreshTokenException());
    }
}
