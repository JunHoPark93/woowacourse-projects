package com.woowacourse.zzazanstagram.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class FileUploaderTest {
    @InjectMocks
    FileUploader fileUploader;

    @Mock
    S3Uploader s3Uploader;

    @Mock
    FileTypeChecker fileTypeChecker;

    @Test
    void imageUploadTest() {
        String dirName = "dirName";
        String imageUrl = "imageUrl";
        MultipartFile multipartFile = new MockMultipartFile("file", "imageTets".getBytes());

        given(fileTypeChecker.validateImageFile(multipartFile)).willReturn(true);
        given(s3Uploader.upload(multipartFile, dirName)).willReturn(imageUrl);

        assertThat(fileUploader.uploadImage(multipartFile, dirName)).isEqualTo(imageUrl);
    }
}