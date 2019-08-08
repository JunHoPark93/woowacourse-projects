package techcourse.myblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.service.dto.UserSession;
import techcourse.myblog.service.dto.request.UserEditRequest;
import techcourse.myblog.service.dto.request.UserLoginRequest;
import techcourse.myblog.service.dto.request.UserRequest;
import techcourse.myblog.service.dto.response.UserResponse;
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
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, EncryptHelper encryptHelper, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.encryptHelper = encryptHelper;
        this.modelMapper = modelMapper;
    }

    public void save(UserRequest userRequest) {
        checkEmail(userRequest);
        User user = createUser(userRequest);
        userRepository.save(user);
    }

    private void checkEmail(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new SignUpException("이미 존재하는 email 입니다");
        }
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

    public UserSession makeSession(UserLoginRequest userLoginRequest) {
        User user = userRepository.findUserByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new LoginException("email 없음"));
        checkPassword(userLoginRequest, user);
        return new UserSession(user.getId(), user.getName(), user.getEmail());
    }

    User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new LoginException("user 없음"));
    }

    private void checkPassword(UserLoginRequest userLoginRequest, User user) {
        if (!encryptHelper.isMatch(userLoginRequest.getPassword(), user.getPassword())) {
            throw new LoginException("비밀번호 틀림");
        }
    }

    @Transactional
    public UserSession editAndFind(Long userId, UserEditRequest userEditRequest) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        changeName(userEditRequest.getName(), user);
        return new UserSession(user.getId(), user.getName(), user.getEmail());
    }

    private void changeName(String name, User user) {
        try {
            user.changeName(name);
        } catch (IllegalArgumentException e) {
            throw new EditException(e.getMessage());
        }
    }

    public void delete(Long userId, UserSession userSession) {
        User user = findById(userId);
        if (!user.matchEmail(userSession.getEmail())) {
            throw new SignUpException("해당 user가 아닙니다");
        }
        userRepository.deleteById(userId);
    }

    public UserResponse find(UserSession userSession) {
        User user = findById(userSession.getId());
        return modelMapper.map(user, UserResponse.class);
    }
}
