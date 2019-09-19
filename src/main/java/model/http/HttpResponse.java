package model.http;

import utils.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private Map<String, String> headers = new HashMap<>();
    private String resource;
    private String location;
    private HttpStatus httpStatus;

    public HttpResponse() {
    }

    public void sendRedirect(String location, HttpStatus httpStatus) {
        this.location = location;
        this.httpStatus = httpStatus;
    }

    public void forward(String resource) {
        this.resource = resource;
    }

    public String getForward() {
        return resource;
    }

    public String getSendRedirect() {
        return location;
    }

    public void sendError(HttpStatus status, String msg) {

    }

    public boolean hasError() {
        // TODO enum error 메시지 확인
        return httpStatus.equals(HttpStatus.FORBIDDEN) || httpStatus.equals(HttpStatus.NOT_ALLOWED);
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getLocation() {
        return location;
    }
}
