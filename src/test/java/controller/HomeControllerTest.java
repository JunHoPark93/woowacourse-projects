package controller;

import org.junit.jupiter.api.Test;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestHeaderParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeControllerTest {
    @Test
    void index_페이지() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/data/http_request.txt");
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);
        HttpRequest httpRequest = RequestHeaderParser.parseRequest(inputStream);
    }
}
