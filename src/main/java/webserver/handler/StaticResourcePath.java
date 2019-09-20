package webserver.handler;

import java.util.Arrays;

public enum StaticResourcePath {
    CSS("/css"),
    JS("/js"),
    FONT("/fonts");

    StaticResourcePath(String folderName) {
        this.folderName = folderName;
    }

    private final String folderName;

    public boolean isMatch(String folderName) {
        return this.folderName.equals(folderName);
    }

    public static boolean contains(String path) {
        return Arrays.stream(values())
                .anyMatch(staticResourcePath -> staticResourcePath.isMatch(path));
    }
}
