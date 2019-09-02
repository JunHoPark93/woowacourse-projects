package com.woowacourse.zzazanstagram.util;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileTypeCheckerTest {

    private static final String TEST_RESOURCES = "src/test/resources/";

    FileTypeChecker fileTypeChecker = new FileTypeChecker();

    @Test
    void image_test() {
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile("file", new FileInputStream(new File(TEST_RESOURCES + "test.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(fileTypeChecker.validateImageFile(multipartFile));
    }

    @Test
    void not_image_test() {
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile("file", new FileInputStream(new File(TEST_RESOURCES + "test2.md")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(fileTypeChecker.validateImageFile(multipartFile));
    }
}