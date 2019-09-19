package webserver;

import handler.Handler;
import handler.HandlerList;
import model.http.HttpRequest;
import model.http.HttpResponse;
import model.http.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
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

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequest httpRequest = RequestHeaderParser.parseRequest(new InputStreamReader(in, StandardCharsets.UTF_8));
            HttpResponse httpResponse = new HttpResponse();

            Handler mappingHandler = getHandler(httpRequest);
            if (mappingHandler == null) {
                // TODO httpResponse.sendError("no handler found");
                throw new RuntimeException();
            }

            ModelAndView mav = mappingHandler.handle(httpRequest, httpResponse);

            // TODO ViewResolver
            if (httpResponse.hasError()) {
                handleOutputStream(out, "error.html", TEMPLATE_PATH);
                return;
            }

            if (mav.isStaticFolderResource()) {
                handleOutputStream(out, mav.getViewName(), STATIC_PATH);
                return;
            }
            handleOutputStream(out, mav.getViewName(), TEMPLATE_PATH);

            //handleOutputStream(out, requestTarget);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Handler getHandler(HttpRequest httpRequest) {
        for (Handler handler : HandlerList.LIST) {
            // TODO optional
            String temp = httpRequest.getDirectory();
            Handler targetHandler = handler.getHandler(temp);
            if (targetHandler != null) {
                return targetHandler;
            }
        }
        return null;
    }

    private void handleOutputStream(OutputStream out, String requestTarget, String dirPath) throws IOException, URISyntaxException {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] body = FileIoUtils.loadFileFromClasspath("./" + dirPath + "/" + requestTarget);

        // TODO 너무 졸려
        if (requestTarget.contains("css")) {
            response200HeaderCSS(dos, body.length);
        } else {
            response200Header(dos, body.length);
        }
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

    private void response200HeaderCSS(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/css;charset=utf-8\r\n");
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
