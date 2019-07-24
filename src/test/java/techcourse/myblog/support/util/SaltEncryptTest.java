package techcourse.myblog.support.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import techcourse.myblog.support.encryptor.EncryptHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaltEncryptTest {
    @Autowired
    private EncryptHelper encryptHelper;

    @Test
    void 비밀번호_검증() {
        String password = "JasonPassword!1";

        String encrypted = encryptHelper.encrypt(password);

        assertTrue(encryptHelper.isMatch(password, encrypted));
    }
}