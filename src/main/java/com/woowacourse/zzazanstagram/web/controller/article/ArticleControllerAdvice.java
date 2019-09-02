package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.exception.ArticleAuthenticationException;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.hashtag.exception.HashtagException;
import com.woowacourse.zzazanstagram.web.message.ApiResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
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

        return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HashtagException.class)
    public ResponseEntity<ApiResponse> handleHashTagException(HashtagException e) {
        log.error("{} HashtagException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<ApiResponse> handleArticleException(ArticleException e) {
        log.error("{} ArticleException >> {}", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "해당 게시글이 없습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class})
    public ResponseEntity<ApiResponse> handleMultipartException(MultipartException e) {
        log.error("{} file size가 너무 큽니다 >> {},", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.PAYLOAD_TOO_LARGE, "file size too large"), HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ApiResponse> handleImageException(FileUploadException e) {
        log.error("{} 업로드 할 파일이 이미지 파일이 아닙니다. >> {} ", TAG, e.getMessage());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
