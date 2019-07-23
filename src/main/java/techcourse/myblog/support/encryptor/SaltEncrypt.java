package techcourse.myblog.support.encryptor;

import org.mindrot.jbcrypt.BCrypt;

public class SaltEncrypt implements EncryptHelper {
    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
