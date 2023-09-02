package com.hiufestainfo.domain.user.usecase.processor;
import com.hiufestainfo.global.annotation.Processor;
import com.hiufestainfo.global.feign.oauth.kakao.RequestKakaoTokenClient;
import com.hiufestainfo.global.feign.oauth.oidc.PublicKeyDto;
import com.hiufestainfo.global.feign.oauth.oidc.PublicKeysDto;
import com.hiufestainfo.global.utils.PublicKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.security.interfaces.RSAPublicKey;

import static com.hiufestainfo.global.constant.StaticValue.KAKAO_PUBLIC_KEYS;

@Processor
@RequiredArgsConstructor
public class PublicKeyProcessor {
    private final RequestKakaoTokenClient requestKakaoTokenClient;

    @Cacheable(value = KAKAO_PUBLIC_KEYS, cacheManager = "redisCacheManager")
    public PublicKeysDto getCachedKakaoPublicKeys(){
        return requestKakaoTokenClient.getPublicKeys();
    }


    public RSAPublicKey generatePublicKey(PublicKeyDto key){
        return PublicKeyGenerator.excute(key.getKty(), key.getN(), key.getE());
    }
}
