package com.woowacourse.zzazanstagram.web.message;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private HttpStatus httpStatus;
    private String msg;

    public ApiResponse(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMsg() {
        return msg;
    }
}
