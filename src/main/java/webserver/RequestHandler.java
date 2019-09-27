package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.handler.ControllerList;
import webserver.handler.controller.DefaultController;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestHeaderParser;
import webserver.http.response.HttpResponse;
import webserver.view.TemplateResourceResolver;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequest httpRequest = RequestHeaderParser.parseRequest(new InputStreamReader(in, StandardCharsets.UTF_8));
            HttpResponse httpResponse = HttpResponse.of();

            ControllerList.CONTROLLERS.stream()
                    .filter(controller -> controller.isMapping(httpRequest))
                    .findFirst()
                    .orElse(new DefaultController(new TemplateResourceResolver()))
                    .service(httpRequest, httpResponse);

            httpResponse.send(out);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
