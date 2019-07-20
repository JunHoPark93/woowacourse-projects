package techcourse.myblog.support.util;

public interface EncryptHelper {
    String encrypt(String password);

    boolean isMatch(String password, String hashed);
}
