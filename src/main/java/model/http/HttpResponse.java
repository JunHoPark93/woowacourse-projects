package model.http;

import utils.HttpStatus;
import utils.MediaType;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    // TODO model 맵의 역할
    private Map<String, String> body = new HashMap<>();
    private String resource;
    private String location;
    private HttpStatus httpStatus;
    private MediaType mediaType;
    private String errorMsg;

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

    public void sendError(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.errorMsg = msg;
    }

    public boolean hasError() {
        return httpStatus.isError();
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
