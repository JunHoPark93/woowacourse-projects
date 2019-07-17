package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import techcourse.myblog.domain.User;
import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.dto.UserDto;
import techcourse.myblog.dto.UserLoginDto;
import techcourse.myblog.dto.UserResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String createLoginForm(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String createSignForm() {
        return "signup";
    }

    @PostMapping("/users")
    public String saveUser(@Valid UserDto userDto, HttpSession httpSession) {
        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword());
        userRepository.save(user);

        // session에 유저정보 저장
        httpSession.setAttribute("user", user);

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

    @GetMapping("/mypage")
    public String myPageForm() {
        return "mypage";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpServletRequest request, Model model) {
        //Optional<User> mayBeUser = userRepository.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        Optional<User> mayBeUser = userRepository.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (!mayBeUser.isPresent()) {
            // error 메세지와 함께 다시 로그인 시도시키기
            model.addAttribute("error", "입력이 틀렸습니다");
            return "login";
        }

        request.getSession().setAttribute("user", mayBeUser.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
