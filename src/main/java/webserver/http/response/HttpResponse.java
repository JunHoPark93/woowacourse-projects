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
    private Map<String, String> headers = new HashMap<>();
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

    public void send(String path, HttpStatus httpStatus) throws IOException, URISyntaxException {
        this.path = path;
        this.body = FileIoUtils.loadFileFromClasspath(path);
        this.httpStatus = httpStatus;
        this.mediaType = MediaType.find(extractExtensions(path));
        createHeader();
    }

    private String extractExtensions(String path) {
        return path.substring(path.lastIndexOf(EXTENSION_DELIMITER) + 1).toUpperCase();
    }

    private void createHeader() {
        if (isRedirect()) {
            headers.put("Location", excludePathPrefix(path) + "\r\n");
        }
        headers.put("Content-Type", mediaType.getContentType() + ";charset=utf-8\r\n");
        headers.put("Content-Length", body.length + ";charset=utf-8\r\n");
    }

    private String excludePathPrefix(String path) {
        return path.substring(path.indexOf(SLASH));
    }

    public void sendError(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.errorMsg = msg;
    }

    private boolean isRedirect() {
        return httpStatus.equals(HttpStatus.REDIRECT);
    }

    public int getHttpStatusCode() {
        return httpStatus.getValue();
    }

    public String getMediaType() {
        return mediaType.getContentType();
    }

    public String getPath() {
        return path;
    }

    public void flush(OutputStream out) {
        DataOutputStream dos = new DataOutputStream(out);
        createResponse(dos);
    }

    private void createResponse(DataOutputStream dos) {
        responseHeader(dos);
        responseBody(dos, this.body);
    }

    private void responseHeader(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 " + httpStatus.getValue() + " " + httpStatus.getReasonPhrase() + " \r\n");
            if (isRedirect()) {
                dos.writeBytes("Location: " + headers.get("Location"));
            }
            dos.writeBytes("Content-Type: " + headers.get("Content-Type") + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + headers.get("Content-Length") + "\r\n");
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
