package webserver.http.request;

public final class RequestBody {
    private final QueryParams body;

    private RequestBody(String body) {
        this.body = QueryParser.parseRequest(body);
    }

    static RequestBody of(String body) {
        return new RequestBody(body);
    }

    public QueryParams getBody() {
        return body;
    }

    public String getParameter(String key) {
        return body.get(key);
    }
}
