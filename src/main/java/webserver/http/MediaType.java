package webserver.http;

import java.util.Arrays;

public enum MediaType {
    HTML("text/html"),
    ICO("text/html"),
    CSS("text/css"),
    JS("text/html"),
    WOFF("text/html"),
    TTF("text/html"),
    NOT_SUPPORT("none");

    private final String contentType;

    MediaType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public static MediaType find(String path) {
        return Arrays.stream(values())
                .filter(mediaType -> mediaType.equals(valueOf(path)))
                .findFirst()
                .orElse(MediaType.NOT_SUPPORT);
    }
}
