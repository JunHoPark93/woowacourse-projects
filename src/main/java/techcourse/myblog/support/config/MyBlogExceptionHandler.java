package techcourse.myblog.support.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import techcourse.myblog.service.dto.request.UserEditRequest;
import techcourse.myblog.service.dto.request.UserLoginRequest;
import techcourse.myblog.service.dto.request.UserRequest;
import techcourse.myblog.service.exception.*;

@ControllerAdvice
public class MyBlogExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public String handleLoginException(LoginException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        model.addAttribute("userLoginRequest", new UserLoginRequest());
        return "login";
    }

    @ExceptionHandler(SignUpException.class)
    public String handleSignUpException(SignUpException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        model.addAttribute("userRequest", new UserRequest());
        return "signup";
    }

    @ExceptionHandler(EditException.class)
    public String handleEditException(EditException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        model.addAttribute("userEditRequest", new UserEditRequest());
        return "mypage-edit";
    }

    @ExceptionHandler(NoArticleException.class)
    public String handleNoArticleException(NoArticleException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "errorpage";
    }

    @ExceptionHandler(InvalidAuthorException.class)
    public String handleInvalidAuthorException(InvalidAuthorException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "redirect:/";
    }

    @ExceptionHandler(NoCommentException.class)
    public String handleNoCommentException(NoCommentException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "redirect:/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNoResourceFoundException(ResourceNotFoundException e, Model model) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        model.addAttribute("error", errorMessage);
        return "redirect:/";
    }
}
