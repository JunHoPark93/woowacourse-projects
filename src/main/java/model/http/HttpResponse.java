package model.http;

import utils.HttpStatus;
import utils.MediaType;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private Map<String, String> headers = new HashMap<>();
    private String resource;
    private String location;
    private HttpStatus httpStatus;
    private MediaType mediaType;

    public HttpResponse() {
    }

    public void sendRedirect(String location, HttpStatus httpStatus) {
        this.location = location;
        this.httpStatus = httpStatus;
        this.mediaType = MediaType.find(location.substring(location.lastIndexOf(".") + 1).toUpperCase());
    }

    public void forward(String resource, HttpStatus httpStatus) {
        this.resource = resource;
        this.httpStatus = httpStatus;
        this.mediaType = MediaType.find(resource.substring(resource.lastIndexOf(".") + 1).toUpperCase());
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

    public String getLocation() {
        return location;
    }

    public int getHttpStatusCode() {
        return httpStatus.getValue();
    }

    public String getHttpReasonPhrase() {
        return httpStatus.getReasonPhrase();
    }

    public String getMediaType() {
        return mediaType.getContentType();
    }

    public boolean isRedirect() {
        return httpStatus.equals(HttpStatus.REDIRECT);
    }
}
