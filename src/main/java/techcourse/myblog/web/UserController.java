package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.dto.UserDto;
import techcourse.myblog.dto.UserResponseDto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String createLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String createSignForm() {
        return "signup";
    }

    @PostMapping("/users")
    public String saveUser(@Valid UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword());
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserResponseDto> users = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            users.add(new UserResponseDto(user.getName(), user.getEmail()));
        }

        model.addAttribute("users", users);
        return "user-list";
    }
}
