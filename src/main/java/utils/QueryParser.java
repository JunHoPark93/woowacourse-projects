package utils;

import java.util.HashMap;
import java.util.Map;

public class QueryParser {
    public static Map<String, String> parseRequest(String path) {
        Map<String, String> queryParams = new HashMap<>();
        String[] queries = path.split("&");

        for (String eachQuery : queries) {
            String[] spliced = eachQuery.split("=");
            queryParams.put(spliced[0], spliced[1]);
        }

        return queryParams;
    }
}
