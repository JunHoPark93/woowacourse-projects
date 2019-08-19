package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = MemberController.class)
public class MemberControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(MemberControllerAdvice.class);
    private static final String TAG = "[MemberControllerAdvice]";

    @ExceptionHandler(MemberEmailFormatException.class)
    public String handleMemberEmailFormatException(MemberEmailFormatException e) {
        log.error("{} MemberEmailFormatException >> {}", TAG, e.getMessage());

        return "redirect:/signup";
    }

    @ExceptionHandler(MemberNameFormatException.class)
    public String handleMemberNameFormatException(MemberNameFormatException e) {
        log.error("{} MemberNameFormatException >> {}", TAG, e.getMessage());

        return "redirect:/signup";
    }

    @ExceptionHandler(MemberNickNameFormatException.class)
    public String handleMemberNickNameFormatException(MemberNickNameFormatException e) {
        log.error("{} MemberNickNameFormatException >> {}", TAG, e.getMessage());

        return "redirect:/signup";
    }

    @ExceptionHandler(MemberPasswordFormatException.class)
    public String handleMemberPasswordFormatException(MemberPasswordFormatException e) {
        log.error("{} MemberPasswordFormatException >> {}", TAG, e.getMessage());

        return "redirect:/signup";
    }

    @ExceptionHandler(MemberProfileUrlFormatException.class)
    public String handleMemberProfileUrlFormatException(MemberProfileUrlFormatException e) {
        log.error("{} MemberPasswordFormatException >> {}", TAG, e.getMessage());

        return "redirect:/signup";
    }

    @ExceptionHandler(MemberSaveException.class)
    public String handleMemberSaveException(MemberSaveException e) {
        log.error("{} MemberSaveException >> {}", TAG, e.getMessage());

        return "redirect:/signup";
    }
}
