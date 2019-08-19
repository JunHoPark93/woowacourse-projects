package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberLoginRequest;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.woowacourse.zzazanstagram.web.SessionKeys.MEMBER;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String loginForm() {
        return "login";
    }

    @PostMapping
    public String login(MemberLoginRequest memberLoginRequest, HttpSession httpSession) {
        MemberResponse memberResponse = loginService.find(memberLoginRequest);
        httpSession.setAttribute(MEMBER, new MemberSession(memberResponse.getEmail(), memberResponse.getNickName(), memberResponse.getProfileImage()));
        return "redirect:/";
    }
}
