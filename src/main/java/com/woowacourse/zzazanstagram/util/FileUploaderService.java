package com.woowacourse.zzazanstagram.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploaderService {
    private final FileUploader fileUploader;
    private final FileTypeChecker fileTypeChecker;

    public FileUploaderService(FileUploader fileUploader, FileTypeChecker fileTypeChecker) {
        this.fileUploader = fileUploader;
        this.fileTypeChecker = fileTypeChecker;
    }

    public String uploadImage(MultipartFile multipartFile, String dirName) {
        if (fileTypeChecker.validateImageFile(multipartFile)) {
            String imageUrl = fileUploader.upload(multipartFile, dirName);
            return imageUrl;
        }
        throw new FileUploadException("이미지 파일이 아닙니다.");
    }
}
