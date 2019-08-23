package com.woowacourse.zzazanstagram.web.controller.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice(assignableTypes = CommentController.class)
public class CommentControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(CommentControllerAdvice.class);
    private static final String TAG = "[CommentControllerAdvice]";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{} MethodArgumentNotValidException >> {}", TAG, e.getMessage());

        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError allError : allErrors) {
            errorMessage.append(allError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}
