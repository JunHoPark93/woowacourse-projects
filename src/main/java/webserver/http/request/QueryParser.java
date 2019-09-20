package webserver.http.request;

import java.util.HashMap;
import java.util.Map;

final class QueryParser {
    private static final String QUERY_DELIMITER = "&";
    private static final String TOKEN_DELIMITER = "=";
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    static Map<String, String> parseRequest(String path) {
        Map<String, String> queryParams = new HashMap<>();
        String[] queries = path.split(QUERY_DELIMITER);

        for (String eachQuery : queries) {
            String[] spliced = eachQuery.split(TOKEN_DELIMITER);
            queryParams.put(spliced[KEY_INDEX], spliced[VALUE_INDEX]);
        }

        return queryParams;
    }
}
