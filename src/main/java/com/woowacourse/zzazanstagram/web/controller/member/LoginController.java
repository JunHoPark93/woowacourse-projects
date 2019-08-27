package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberLoginRequest;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

import static com.woowacourse.zzazanstagram.web.SessionKeys.MEMBER;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private static final String TAG = "[LoginController]";

    private final LoginService loginService;
    private final Map<String, MemberResponse> sessionMap;

    public LoginController(LoginService loginService, Map<String, MemberResponse> sessionMap) {
        this.loginService = loginService;
        this.sessionMap = sessionMap;
    }

    @GetMapping
    public String loginForm() {
        return "login";
    }

    @PostMapping
    public String login(MemberLoginRequest memberLoginRequest, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        MemberResponse memberResponse = loginService.find(memberLoginRequest);
        httpSession.setAttribute(MEMBER, new MemberSession(memberResponse.getId(), memberResponse.getName(), memberResponse.getEmail(), memberResponse.getNickName(), memberResponse.getProfileImage()));
        String randomEndPoint = setEndPoint(memberResponse);
        redirectAttributes.addFlashAttribute("endpoint", randomEndPoint);

        log.info("{} logged in member name : {}", TAG, memberResponse.getName());
        return "redirect:/";
    }

    private String setEndPoint(MemberResponse memberResponse) {
        String randomEndpoint = generateRandomEndPoint();
        sessionMap.put(randomEndpoint, memberResponse);
        return randomEndpoint;
    }

    private String generateRandomEndPoint() {
        return UUID.randomUUID().toString();
    }
}
