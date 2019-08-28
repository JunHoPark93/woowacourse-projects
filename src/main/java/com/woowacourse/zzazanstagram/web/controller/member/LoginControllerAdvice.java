package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.exception.MemberLoginFailException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO responseENtitiy로 error 내리기
@ControllerAdvice(assignableTypes = LoginController.class)
public class LoginControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(LoginControllerAdvice.class);
    private static final String TAG = "[LoginControllerAdvice]";

    @ExceptionHandler(MemberLoginFailException.class)
    public String handleMemberLoginFailException(MemberLoginFailException e) {
        log.error("{} MemberLoginFailException >> {}", TAG, e.getMessage());

        return "redirect:/login";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(MemberNotFoundException e) {
        log.error("{} MemberNotFoundException >> {}", TAG, e.getMessage());

        return "redirect:/login";
    }


}
