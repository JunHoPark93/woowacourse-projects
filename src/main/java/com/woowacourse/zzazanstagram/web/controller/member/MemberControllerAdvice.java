package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.exception.*;
import com.woowacourse.zzazanstagram.web.message.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = MemberController.class)
public class MemberControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(MemberControllerAdvice.class);
    private static final String TAG = "[MemberControllerAdvice]";

    @ExceptionHandler(MemberEmailFormatException.class)
    public ResponseEntity<ApiResponse> handleMemberEmailFormatException(MemberEmailFormatException e) {
        log.error("{} MemberEmailFormatException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNameFormatException.class)
    public ResponseEntity<ApiResponse> handleMemberNameFormatException(MemberNameFormatException e) {
        log.error("{} MemberNameFormatException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNickNameFormatException.class)
    public ResponseEntity<ApiResponse> handleMemberNickNameFormatException(MemberNickNameFormatException e) {
        log.error("{} MemberNickNameFormatException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberPasswordFormatException.class)
    public ResponseEntity<ApiResponse> handleMemberPasswordFormatException(MemberPasswordFormatException e) {
        log.error("{} MemberPasswordFormatException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberProfileUrlFormatException.class)
    public ResponseEntity<ApiResponse> handleMemberProfileUrlFormatException(MemberProfileUrlFormatException e) {
        log.error("{} MemberPasswordFormatException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberSaveException.class)
    public ResponseEntity<ApiResponse> handleMemberSaveException(MemberSaveException e) {
        log.error("{} MemberSaveException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
