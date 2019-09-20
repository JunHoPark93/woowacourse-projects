package webserver.http;

public enum Body {
    EXISTS(true),
    NOT_EXISTS(false);

    private final boolean isExist;

    Body(boolean isExist) {
        this.isExist = isExist;
    }

    public boolean isExists() {
        return isExist;
    }
}
