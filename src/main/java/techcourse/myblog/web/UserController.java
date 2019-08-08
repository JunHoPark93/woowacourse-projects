package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.service.UserService;
import techcourse.myblog.service.dto.UserSession;
import techcourse.myblog.service.dto.request.UserEditRequest;
import techcourse.myblog.service.dto.request.UserLoginRequest;
import techcourse.myblog.service.dto.request.UserRequest;
import techcourse.myblog.service.dto.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {
    private static final String USER = "user";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String createLoginForm(HttpServletRequest request, UserLoginRequest userLoginRequest) {
        if (request.getSession().getAttribute(USER) == null) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String createSignForm(UserRequest userRequest) {
        return "signup";
    }

    @PostMapping("/users")
    public String saveUser(@Valid UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.save(userRequest);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/mypage")
    public String myPageForm(Model model, UserSession userSession) {
        UserResponse userResponse = userService.find(userSession);
        model.addAttribute(USER, userResponse);
        return "mypage";
    }

    @GetMapping("/mypage-edit")
    public String myPageEditForm(UserEditRequest userEditRequest) {
        return "mypage-edit";
    }

    @PostMapping("/login")
    public String login(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        UserSession userSession = userService.makeSession(userLoginRequest);
        request.getSession().setAttribute(USER, userSession);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER);
        return "redirect:/";
    }

    @PutMapping("/users/{userId}")
    public String editUser(@PathVariable("userId") Long userId, HttpServletRequest request, @Valid UserEditRequest userEditRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "mypage-edit";
        }
        UserSession userSession = userService.editAndFind(userId, userEditRequest);
        request.getSession().setAttribute(USER, userSession);
        return "redirect:/";
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId, HttpServletRequest request, UserSession userSession) {
        userService.delete(userId, userSession);
        request.getSession().removeAttribute(USER);
        return "redirect:/";
    }
}
