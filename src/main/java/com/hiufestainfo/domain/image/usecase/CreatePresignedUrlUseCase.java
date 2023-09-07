package com.hiufestainfo.domain.image.usecase;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.hiufestainfo.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class CreatePresignedUrlUseCase {
    private final AmazonS3 amazonS3;
    private final GeneratePresignedUrlProcessor generatePresignedUrlProcessor;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String execute(String prefix) {
        String fileName = UUID.randomUUID().toString();
        if (!prefix.equals("")) {
            fileName = prefix + "/" + fileName;
        }
        GeneratePresignedUrlRequest generatePresignedUrlRequest = generatePresignedUrlProcessor.execute(fileName);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
