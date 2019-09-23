package webserver.handler.controller;

import webserver.http.request.HttpRequest;
import webserver.http.request.RequestHeaderParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestHelper {
    public static HttpRequest createHttpRequest(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);
        return RequestHeaderParser.parseRequest(inputStream);
    }
}
