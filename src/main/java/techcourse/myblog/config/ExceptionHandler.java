package techcourse.myblog.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import techcourse.myblog.exception.EditException;
import techcourse.myblog.exception.ErrorMessage;
import techcourse.myblog.exception.LoginException;
import techcourse.myblog.exception.SignUpException;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(LoginException.class)
    public String handleLoginException(LoginException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "login";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SignUpException.class)
    public String handleSignUpException(SignUpException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "signup";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EditException.class)
    public String handleEditException(EditException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "mypage-edit";
    }
}
