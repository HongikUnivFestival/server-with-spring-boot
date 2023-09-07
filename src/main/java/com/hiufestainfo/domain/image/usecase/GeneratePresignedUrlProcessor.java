package com.hiufestainfo.domain.image.usecase;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.hiufestainfo.global.annotation.Processor;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;

@Processor
public class GeneratePresignedUrlProcessor {

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public GeneratePresignedUrlRequest execute(String fileName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60;
        expiration.setTime(expTimeMillis);
        return expiration;
    }

}