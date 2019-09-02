package com.woowacourse.zzazanstagram.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class S3Uploader {
    private static final Logger log = LoggerFactory.getLogger(S3Uploader.class);
    private static final String TAG = "[S3Uploader]";

    private final AmazonS3 amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3Uploader(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String upload(MultipartFile uploadFile, String dirName) {
        String fileName = dirName + "/" + LocalDateTime.now() + uploadFile.getName();
        log.info("{} uploading image... filename>> {}", TAG, fileName);
        return putS3(uploadFile, fileName);
    }

    private String putS3(MultipartFile uploadFile, String fileName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(uploadFile.getContentType());
        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.error("{} uploading image... filename>> {}", TAG, fileName);
            throw new FileUploadException("파일 업로드 실패");
        }
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}