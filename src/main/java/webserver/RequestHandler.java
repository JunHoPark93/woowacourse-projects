package webserver;

import controller.Controller;
import model.http.HttpRequest;
import model.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
import utils.HandlerMapper;
import utils.RequestHeaderParser;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private static final String STATIC_PATH = "static";
    private static final String TEMPLATE_PATH = "templates";

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        // TODO mapping handler에서 url을 못찾으면, static url인지 검사

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {

            // method를 구분

            // method에 따른 처리 get / post

            // HttpRequest , HttpResponse 빈 객체 호 new HttpResponse(out);
            HttpRequest httpRequest = RequestHeaderParser.parseRequest(new InputStreamReader(in, StandardCharsets.UTF_8));
            HttpResponse httpResponse = new HttpResponse();

            Controller controller = HandlerMapper.findController(httpRequest.getDirectory());
            if (controller == null) {
                // TODO css 리턴 handleStaticResources
                httpResponse.forward(httpRequest.getPath());
                handleOutputStream(out, httpResponse.getForward(), STATIC_PATH);
                return;
            }

            controller.service(httpRequest, httpResponse);

            handleOutputStream(out, httpResponse.getSendRedirect(), TEMPLATE_PATH);

            //handleOutputStream(out, requestTarget);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void handleOutputStream(OutputStream out, String requestTarget, String dirPath) throws IOException, URISyntaxException {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] body = FileIoUtils.loadFileFromClasspath("./" + dirPath + "/" + requestTarget);
        response200Header(dos, body.length);
        responseBody(dos, body);
    }

    private String getRequestedTarget(InputStream in) throws IOException {
        HttpRequest httpRequest = RequestHeaderParser.parseRequest(new InputStreamReader(in, StandardCharsets.UTF_8));
        return httpRequest.getResource();
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
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
