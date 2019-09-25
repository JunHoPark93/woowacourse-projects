package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
import webserver.http.HttpStatus;
import webserver.http.MediaType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private static final String EXTENSION_DELIMITER = ".";
    private static final String SLASH = "/";

    // TODO model 맵의 역할
    private Map<String, String> model = new HashMap<>();
    private ResponseHeader responseHeader = new ResponseHeader();
    private Cookie cookie = Cookie.emptyCookie();
    private String path;
    private HttpStatus httpStatus;
    private MediaType mediaType;
    private String errorMsg;
    private byte[] body;

    private HttpResponse() {
        this.httpStatus = HttpStatus.DEFAULT;
    }

    public static HttpResponse of() {
        return new HttpResponse();
    }

    public void ok(String path) throws IOException, URISyntaxException {
        this.path = path;
        this.body = FileIoUtils.loadFileFromClasspath(path);
        this.httpStatus = HttpStatus.OK;
        this.mediaType = MediaType.find(extractExtensions(path));
        createHeader();
    }

    private String extractExtensions(String path) {
        return path.substring(path.lastIndexOf(EXTENSION_DELIMITER) + 1).toUpperCase();
    }

    private void createHeader() {
        if (isRedirect()) {
            responseHeader.add("Location", excludePathPrefix(path));
        }
        if (cookie.isCookieExists()) {
            responseHeader.add("Set-Cookie", cookie.create());
        }
        responseHeader.add("Content-Type", mediaType.getContentType());
        responseHeader.add("Content-Length", body.length + ";charset=utf-8\r\n");
    }

    private boolean isRedirect() {
        return httpStatus.equals(HttpStatus.REDIRECT);
    }

    private String excludePathPrefix(String path) {
        return path.substring(path.indexOf(SLASH));
    }

    public void redirect(String path) throws IOException, URISyntaxException {
        this.path = path;
        this.body = FileIoUtils.loadFileFromClasspath(path);
        this.httpStatus = HttpStatus.REDIRECT;
        this.mediaType = MediaType.find(extractExtensions(path));
        createHeader();
    }

    public void sendError(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.errorMsg = msg;
    }

    public void addCookie(String key, String value) {
        cookie.add(key, value);
    }

    public int getHttpStatusCode() {
        return httpStatus.getValue();
    }

    public String getPath() {
        return path;
    }

    public void flush(OutputStream out) {
        DataOutputStream dos = new DataOutputStream(out);
        createResponse(dos);
    }

    public String getHeaders(String key) {
        return responseHeader.get(key);
    }

    private void createResponse(DataOutputStream dos) {
        responseHeader(dos);
        responseBody(dos, this.body);
    }

    private void responseHeader(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 " + httpStatus.getValue() + " " + httpStatus.getReasonPhrase() + " \r\n");
            if (responseHeader.contains("Location")) {
                dos.writeBytes("Location: " + responseHeader.get("Location") + "\r\n");
            }
            if (responseHeader.contains("Set-Cookie")) {
                dos.writeBytes("Set-Cookie: " + responseHeader.get("Set-Cookie") + "\r\n");
            }
            dos.writeBytes("Content-Type: " + responseHeader.get("Content-Type") + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + body.length + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
