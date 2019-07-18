package techcourse.myblog.service;

import org.springframework.stereotype.Service;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.dto.UserDto;
import techcourse.myblog.dto.UserLoginDto;
import techcourse.myblog.exception.EditException;
import techcourse.myblog.exception.LoginException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword());
        return userRepository.save(user);
    }

    public Iterable<? extends User> findAll() {
        return userRepository.findAll();
    }

    public User findUserByEmail(UserLoginDto userLoginDto) {
        return userRepository.findUserByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new LoginException("email 없음"));
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
            throw new EditException("유효한 이름이 아닙니다.");
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
