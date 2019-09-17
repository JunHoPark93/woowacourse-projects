package utils;

import model.HttpRequest;
import model.RequestBody;
import model.RequestHeader;
import model.StartLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestHeaderParser {
    public static HttpRequest parseRequest(InputStreamReader inputStream) throws IOException {
        BufferedReader br = new BufferedReader(inputStream);
        String line = br.readLine();
        String[] startLines = line.split(" ");
        StartLine startLine = new StartLine(startLines[0], startLines[1].substring(1), startLines[2]);

        RequestHeader requestHeader = new RequestHeader();

        while (true) {
            line = br.readLine();
            if (line.equals("")) {
                break;
            }
            String[] headers = line.split(": ");
            requestHeader.add(headers[0], headers[1]);
        }

        if (startLine.isBodyExists()) {
            StringBuilder sb = new StringBuilder();
            String bodyLine = br.readLine();
            do {
                sb.append(bodyLine);
                bodyLine = br.readLine();
            } while (bodyLine != null);

            RequestBody requestBody = new RequestBody(sb.toString());

            return new HttpRequest(startLine, requestHeader, requestBody);
        }

        return new HttpRequest(startLine, requestHeader);
    }
}
