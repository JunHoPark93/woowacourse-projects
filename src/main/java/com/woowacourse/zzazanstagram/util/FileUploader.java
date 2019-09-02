package com.woowacourse.zzazanstagram.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader {
    private final S3Uploader s3Uploader;
    private final FileTypeChecker fileTypeChecker;

    public FileUploader(S3Uploader s3Uploader, FileTypeChecker fileTypeChecker) {
        this.s3Uploader = s3Uploader;
        this.fileTypeChecker = fileTypeChecker;
    }

    public String uploadImage(MultipartFile multipartFile, String dirName) {
        if (fileTypeChecker.validateImageFile(multipartFile)) {
            String imageUrl = s3Uploader.upload(multipartFile, dirName);
            return imageUrl;
        }
        throw new FileUploadException("이미지 파일이 아닙니다.");
    }
}
