package utils;

import model.HttpRequest;
import model.RequestBody;
import model.RequestHeader;
import model.RequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestHeaderParser {
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
            StringBuilder sb = new StringBuilder();
            String bodyLine = br.readLine();
            do {
                sb.append(bodyLine);
                bodyLine = br.readLine();
            } while (bodyLine != null);

            RequestBody requestBody = new RequestBody(sb.toString());

            return new HttpRequest(requestLine, requestHeader, requestBody);
        }

        return new HttpRequest(requestLine, requestHeader);
    }

    private static RequestHeader extractRequestHeaders(BufferedReader br) throws IOException {
        RequestHeader requestHeader = new RequestHeader();

        while (true) {
            String line = br.readLine();
            if (line.equals(END_OF_REQUEST_HEADER)) {
                break;
            }
            String[] headers = line.split(HEADER_SEPARATOR);
            requestHeader.add(headers[HEADER_KEY], headers[HEADER_VALUE]);
        }
        return requestHeader;
    }

    private static RequestLine extractRequestLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        String[] startLines = line.split(BLANK);
        return new RequestLine(startLines[METHOD_INDEX], startLines[TARGET_INDEX].substring(1), startLines[VERSION_INDEX]);
    }
}
