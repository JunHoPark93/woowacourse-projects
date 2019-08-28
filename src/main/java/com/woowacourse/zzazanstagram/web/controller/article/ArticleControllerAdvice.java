package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.exception.ArticleAuthenticationException;
import com.woowacourse.zzazanstagram.model.hashtag.exception.HashTagException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO Error class 만들기
@ControllerAdvice(assignableTypes = ArticleController.class)
public class ArticleControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private static final String TAG = "[ArticleControllerAdvice]";

    @ExceptionHandler(ArticleAuthenticationException.class)
    public ResponseEntity<String> handleArticleAuthenticationException(ArticleAuthenticationException e) {
        log.error("{} ArticleAuthenticationException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>("ERROR", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HashTagException.class)
    public ResponseEntity<String> handleHashTagException(HashTagException e) {
        log.error("{} HashTagException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>("ERROR", HttpStatus.UNAUTHORIZED);
    }
}
