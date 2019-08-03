package techcourse.myblog.support.util;

import org.junit.jupiter.api.Test;
import techcourse.myblog.support.encryptor.EncryptHelper;
import techcourse.myblog.support.encryptor.SaltEncrypt;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SaltEncryptTest {
    private EncryptHelper encryptHelper = new SaltEncrypt();

    @Test
    void 비밀번호_검증() {
        String password = "JasonPassword!1";

        String encrypted = encryptHelper.encrypt(password);

        assertTrue(encryptHelper.isMatch(password, encrypted));
    }
}