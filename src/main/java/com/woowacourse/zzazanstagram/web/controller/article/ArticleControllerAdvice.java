package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.exception.ArticleAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ArticleController.class)
public class ArticleControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private static final String TAG = "[MemberControllerAdvice]";

    @ExceptionHandler(ArticleAuthenticationException.class)
    public ResponseEntity<String> handleArticleAuthenticationException(ArticleAuthenticationException e) {
        log.error("{} ArticleAuthenticationException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>("ERROR", HttpStatus.UNAUTHORIZED);
    }
}