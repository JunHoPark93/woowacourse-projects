package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.exception.MemberEmailFormatException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberLoginFailException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberNotFoundException;
import com.woowacourse.zzazanstagram.web.message.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = LoginController.class)
public class LoginControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(LoginControllerAdvice.class);
    private static final String TAG = "[LoginControllerAdvice]";

    @ExceptionHandler(MemberLoginFailException.class)
    public ResponseEntity<ApiResponse> handleMemberLoginFailException(MemberLoginFailException e) {
        log.info("{} MemberLoginFailException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiResponse> handleMemberNotFoundException(MemberNotFoundException e) {
        log.error("{} MemberNotFoundException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberEmailFormatException.class)
    public ResponseEntity<ApiResponse> handleMemberEmailFormatException(MemberEmailFormatException e) {
        log.error("{} MemberEmailFormatException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
