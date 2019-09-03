package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberLoginRequest;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.LoginService;
import com.woowacourse.zzazanstagram.util.SocketEndPointManager;
import com.woowacourse.zzazanstagram.web.SessionKeys;
import com.woowacourse.zzazanstagram.web.message.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.net.URI;

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
    public ResponseEntity<ApiResponse> login(MemberLoginRequest memberLoginRequest, HttpSession httpSession) {
        MemberResponse memberResponse = loginService.findMemberResponse(memberLoginRequest);
        setSession(httpSession, memberResponse);

        log.info("{} logged in member name : {}", TAG, memberResponse.getName());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.MOVED_PERMANENTLY,
                "login success"), setLoginHeader(memberResponse), HttpStatus.MOVED_PERMANENTLY);
    }

    private HttpHeaders setLoginHeader(MemberResponse memberResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        headers.add("Set-Cookie", "endpoint=" + socketEndPointManager.createEndPoint(memberResponse));
        return headers;
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
