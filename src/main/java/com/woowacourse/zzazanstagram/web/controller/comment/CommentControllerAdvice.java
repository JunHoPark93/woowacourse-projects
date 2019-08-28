package com.woowacourse.zzazanstagram.web.controller.comment;

import com.woowacourse.zzazanstagram.model.comment.exception.CommentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = CommentController.class)
public class CommentControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(CommentControllerAdvice.class);
    private static final String TAG = "[CommentControllerAdvice]";

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(CommentException e) {
        log.error("{} MethodArgumentNotValidException >> {}", TAG, e.getMessage());

        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
