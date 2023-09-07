package com.hiufestainfo.domain.image.controller;

import com.hiufestainfo.domain.image.usecase.CreatePresignedUrlUseCase;
import com.hiufestainfo.global.response.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/image")
@Api(tags = {"이미지 PreSigned Url API"})
public class ImageController {
    private final CreatePresignedUrlUseCase createPresignedUrlUseCase;

    @Operation(summary = "dj 이미지용입니다.")
    @GetMapping("/dj")
    public SuccessResponse<Object> getDjPresignedUrl() {
        String preSignedUrl = createPresignedUrlUseCase.execute("dj");
        return SuccessResponse.of(preSignedUrl);
    }

    @Operation(summary = "promotion 이미지용입니다.")
    @GetMapping("/promotion")
    public SuccessResponse<Object> getPromotionPresignedUrl() {
        String preSignedUrl = createPresignedUrlUseCase.execute("promotion");
        return SuccessResponse.of(preSignedUrl);
    }

    @Operation(summary = "pub 이미지용입니다.")
    @GetMapping("/pub")
    public SuccessResponse<Object> getPubPresignedUrl() {
        String preSignedUrl = createPresignedUrlUseCase.execute("pub");
        return SuccessResponse.of(preSignedUrl);
    }
}
