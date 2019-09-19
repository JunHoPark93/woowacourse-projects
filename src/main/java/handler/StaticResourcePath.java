package handler;

public enum StaticResourcePath {
    CSS("/css/"),
    JS("/js/"),
    FONT("/fonts/");

    StaticResourcePath(String folderName) {
        this.folderName = folderName;
    }

    private final String folderName;

    public boolean isMatch(String folderName) {
        return this.folderName.equals(folderName);
    }

    public static boolean contains(String path) {
        for (StaticResourcePath value : values()) {
            if (value.isMatch(path)) {
                return true;
            }
        }
        return false;
    }
}
