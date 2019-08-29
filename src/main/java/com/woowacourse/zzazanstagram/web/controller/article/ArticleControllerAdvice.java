package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.exception.ArticleAuthenticationException;
import com.woowacourse.zzazanstagram.model.hashtag.exception.HashtagException;
import com.woowacourse.zzazanstagram.web.message.ApiResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ArticleControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private static final String TAG = "[ArticleControllerAdvice]";

    @ExceptionHandler(ArticleAuthenticationException.class)
    public ResponseEntity<ApiResponse> handleArticleAuthenticationException(ArticleAuthenticationException e) {
        log.error("{} ArticleAuthenticationException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "게시글에 대한 권한이 없습니다"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HashtagException.class)
    public ResponseEntity<String> handleHashTagException(HashtagException e) {
        log.error("{} HashtagException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>("ERROR", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class})
    public ResponseEntity<ApiResponse> handleMultipartException(MultipartException e) {
        log.error("{} file size가 너무 큽니다 >> {},", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.PAYLOAD_TOO_LARGE, "file size too large"), HttpStatus.PAYLOAD_TOO_LARGE);
    }
}
