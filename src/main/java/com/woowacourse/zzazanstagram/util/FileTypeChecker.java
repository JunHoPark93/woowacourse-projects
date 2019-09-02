package com.woowacourse.zzazanstagram.util;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileTypeChecker {
    private final Tika tika = new Tika();

    public boolean validateImageFile(MultipartFile multipartFile) {
        String fileType = findFileType(multipartFile);
        return fileType.toLowerCase().startsWith("image");
    }

    private String findFileType(MultipartFile multipartFile) {
        try {
            return tika.detect(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new FileUploadException("허용 되지 않는 파일입니다.");
        }
    }
}
