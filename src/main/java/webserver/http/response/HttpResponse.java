package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.view.ViewResolveResult;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private static final String EXTENSION_DELIMITER = ".";

    private ResponseHeader responseHeader = new ResponseHeader();
    private Cookie cookie = Cookie.newInstance();
    private HttpStatus httpStatus;
    private MediaType mediaType;
    private String errorMsg;
    private ViewResolveResult viewResolveResult;

    private HttpResponse() {
        this.httpStatus = HttpStatus.DEFAULT;
    }

    public static HttpResponse of() {
        return new HttpResponse();
    }

    public void ok(ViewResolveResult viewResolveResult) {
        this.viewResolveResult = viewResolveResult;
        this.httpStatus = HttpStatus.OK;
        this.mediaType = MediaType.find(extractExtensions(viewResolveResult.getPath()));
        createHeader();
    }

    private String extractExtensions(String path) {
        return path.substring(path.lastIndexOf(EXTENSION_DELIMITER) + 1).toUpperCase();
    }

    private void createHeader() {
        if (isRedirect()) {
            responseHeader.add("Location", viewResolveResult.getPath());
        }
        if (cookie.isCookieExists()) {
            responseHeader.add("Set-Cookie", cookie.create());
        }
        responseHeader.add("Content-Type", mediaType.getContentType());
        responseHeader.add("Content-Length", viewResolveResult.getBodyLength() + ";charset=utf-8\r\n");
    }

    private boolean isRedirect() {
        return httpStatus.equals(HttpStatus.REDIRECT);
    }

    public void redirect(ViewResolveResult viewResolveResult) {
        this.viewResolveResult = viewResolveResult;
        this.httpStatus = HttpStatus.REDIRECT;
        this.mediaType = MediaType.find(extractExtensions(viewResolveResult.getPath()));
        createHeader();
    }

    public void sendError(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.errorMsg = msg;
    }

    public void addCookie(String key, String value) {
        cookie.add(key, value);
    }

    public void addCookieValues(String key, String addValue) {
        cookie.addAttribute(key, addValue);
    }

    public void setCookieHttpOnly(String key) {
        cookie.setHttpOnly(key);
    }

    public int getHttpStatusCode() {
        return httpStatus.getValue();
    }

    public void flush(OutputStream out) {
        DataOutputStream dos = new DataOutputStream(out);
        createResponse(dos);
    }

    public String getHeaders(String key) {
        return responseHeader.get(key);
    }

    public String getPath() {
        return viewResolveResult.getPath();
    }

    private void createResponse(DataOutputStream dos) {
        responseHeader(dos);
        responseBody(dos, viewResolveResult.getBody());
    }

    private void responseHeader(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 " + httpStatus.getValue() + " " + httpStatus.getReasonPhrase() + " \r\n");
            if (responseHeader.contains("Location")) {
                dos.writeBytes("Location: " + responseHeader.get("Location") + "\r\n");
            }
            if (responseHeader.contains("Set-Cookie")) {
                setEachCookie(dos);
            }
            dos.writeBytes("Content-Type: " + responseHeader.get("Content-Type") + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + viewResolveResult.getBodyLength() + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void setEachCookie(DataOutputStream dos) throws IOException {
        String[] split = responseHeader.get("Set-Cookie").split("\r\n");
        for (String s : split) {
            dos.writeBytes("Set-Cookie: " + s + "\r\n");
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
