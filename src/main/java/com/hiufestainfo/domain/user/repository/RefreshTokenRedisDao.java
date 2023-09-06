package com.hiufestainfo.domain.user.repository;

import com.hiufestainfo.domain.user.entity.RefreshToken;
import com.hiufestainfo.global.annotation.RedisRepository;
import com.hiufestainfo.global.redis.base.BaseRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import static com.hiufestainfo.global.constant.StaticValue.REFRESH_TOKEN_KEY;


@RedisRepository
public class RefreshTokenRedisDao extends BaseRedisRepository<RefreshToken> {
    @Autowired
    public RefreshTokenRedisDao(RedisTemplate redisTemplate) {
        this.prefix = REFRESH_TOKEN_KEY + ":";
        this.redisTemplate = redisTemplate;
    }
}
