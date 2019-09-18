package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;
import org.junit.jupiter.api.Test;
import utils.HttpStatus;
import utils.RequestHeaderParser;

import java.io.*;
import java.net.Socket;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeControllerTest {
    @Test
    void index_페이지() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/data/http_request.txt");
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);
        HttpRequest httpRequest = RequestHeaderParser.parseRequest(inputStream);

//        Socket socket = new Socket();
//        HttpResponse httpResponse = new HttpResponse(socket.getOutputStream());
//
//        Controller controller = new HomeController();
//        controller.service(httpRequest, httpResponse);
//
//        assertThat(httpResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
    }
}
