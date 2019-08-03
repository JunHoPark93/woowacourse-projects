package techcourse.myblog.service;

import org.springframework.stereotype.Service;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.service.dto.UserLoginRequest;
import techcourse.myblog.service.dto.UserRequest;
import techcourse.myblog.service.exception.EditException;
import techcourse.myblog.service.exception.LoginException;
import techcourse.myblog.service.exception.SignUpException;
import techcourse.myblog.support.encryptor.EncryptHelper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private EncryptHelper encryptHelper;

    public UserService(UserRepository userRepository, EncryptHelper encryptHelper) {
        this.userRepository = userRepository;
        this.encryptHelper = encryptHelper;
    }

    public User saveUser(UserRequest userRequest) {
        User user = createUser(userRequest);
        return userRepository.save(user);
    }

    private User createUser(UserRequest userRequest) {
        try {
            return new User(userRequest.getName(), userRequest.getEmail(), encryptHelper.encrypt(userRequest.getPassword()));
        } catch (IllegalArgumentException e) {
            throw new SignUpException(e.getMessage());
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserByEmail(UserLoginRequest userLoginRequest) {
        User user = userRepository.findUserByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new LoginException("email 없음"));

        checkPassword(userLoginRequest, user);
        return user;
    }

    private void checkPassword(UserLoginRequest userLoginRequest, User user) {
        if (!encryptHelper.isMatch(userLoginRequest.getPassword(), user.getPassword())) {
            throw new LoginException("비밀번호 틀림");
        }
    }

    @Transactional
    public User editUserName(Long userId, String name) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        changeName(name, user);
        return user;
    }

    private void changeName(String name, User user) {
        try {
            user.changeName(name);
        } catch (IllegalArgumentException e) {
            throw new EditException(e.getMessage());
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
