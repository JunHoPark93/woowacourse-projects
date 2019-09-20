package webserver.http.request;

import utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class RequestHeaderParser {
    private static final String BLANK = " ";
    private static final String HEADER_SEPARATOR = ": ";
    private static final String END_OF_REQUEST_HEADER = "";
    private static final int METHOD_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    private static final int VERSION_INDEX = 2;
    private static final int HEADER_KEY = 0;
    private static final int HEADER_VALUE = 1;

    public static HttpRequest parseRequest(InputStreamReader inputStream) throws IOException {
        BufferedReader br = new BufferedReader(inputStream);
        RequestLine requestLine = extractRequestLine(br);
        RequestHeader requestHeader = extractRequestHeaders(br);

        if (requestLine.isBodyExists()) {
            String body = IOUtils.readData(br, requestHeader.getContentLength());
            RequestBody requestBody = RequestBody.of(body);

            return HttpRequest.createWithBody(requestLine, requestHeader, requestBody);
        }

        return HttpRequest.createWithoutBody(requestLine, requestHeader);
    }


    private static RequestLine extractRequestLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        String[] startLines = line.split(BLANK);
        return RequestLine.from(startLines[METHOD_INDEX], startLines[TARGET_INDEX], startLines[VERSION_INDEX]);
    }

    private static RequestHeader extractRequestHeaders(BufferedReader br) throws IOException {
        Map<String, String> requestHeaders = new HashMap<>();

        while (true) {
            String line = br.readLine();
            if (line.equals(END_OF_REQUEST_HEADER)) {
                break;
            }
            String[] headers = line.split(HEADER_SEPARATOR);
            requestHeaders.put(headers[HEADER_KEY], headers[HEADER_VALUE]);
        }

        return RequestHeader.of(RequestHeaderParams.of(requestHeaders));
    }
}
