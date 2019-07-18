package techcourse.myblog.service;

import org.springframework.stereotype.Service;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.dto.UserDto;
import techcourse.myblog.dto.UserLoginDto;
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
        user.changeName(name);
        return user;
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
