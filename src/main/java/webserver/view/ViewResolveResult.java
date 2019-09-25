package webserver.view;

public class ViewResolveResult {
    private final byte[] body;
    private final String path;

    public ViewResolveResult(byte[] body, String path) {
        this.body = body;
        this.path = path;
    }

    public byte[] getBody() {
        return body;
    }

    public String getPath() {
        return path;
    }

    public int getBodyLength() {
        return body.length;
    }
}
