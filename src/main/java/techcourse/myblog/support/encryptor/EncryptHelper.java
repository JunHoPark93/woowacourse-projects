package techcourse.myblog.support.encryptor;

public interface EncryptHelper {
    String encrypt(String password);

    boolean isMatch(String password, String hashed);
}
