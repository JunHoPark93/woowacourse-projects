package model.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private Map<String, String> headers = new HashMap<>();
    private String resource;
    private String location;

    public HttpResponse() {
    }

    public void sendRedirect(String location) {
        this.location = location;
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
}
