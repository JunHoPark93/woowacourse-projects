package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberLoginRequest;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.LoginService;
import com.woowacourse.zzazanstagram.util.SocketEndPointManager;
import com.woowacourse.zzazanstagram.web.SessionKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static com.woowacourse.zzazanstagram.web.SessionKeys.MEMBER;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private static final String TAG = "[LoginController]";

    private final LoginService loginService;
    private final SocketEndPointManager socketEndPointManager;

    public LoginController(LoginService loginService, SocketEndPointManager socketEndPointManager) {
        this.loginService = loginService;
        this.socketEndPointManager = socketEndPointManager;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(MemberLoginRequest memberLoginRequest, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        MemberResponse memberResponse = loginService.findMemberResponse(memberLoginRequest);
        setSession(httpSession, memberResponse);
        redirectAttributes.addFlashAttribute("endpoint", socketEndPointManager.createEndPoint(memberResponse));

        log.info("{} logged in member name : {}", TAG, memberResponse.getName());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        MemberSession memberSession = (MemberSession) httpSession.getAttribute(SessionKeys.MEMBER);
        socketEndPointManager.removeMember(memberSession);
        httpSession.removeAttribute(SessionKeys.MEMBER);
        return "redirect:/login";
    }

    private void setSession(HttpSession httpSession, MemberResponse memberResponse) {
        httpSession.setAttribute(MEMBER, new MemberSession(memberResponse.getId(), memberResponse.getName(), memberResponse.getEmail(),
                memberResponse.getNickName(), memberResponse.getProfileImage()));
    }
}
