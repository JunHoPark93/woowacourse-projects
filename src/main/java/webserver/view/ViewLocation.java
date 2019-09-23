package webserver.view;

public enum ViewLocation {
    TEMPLATE("templates"),
    STATIC("static"),
    ROOT("");

    private final String location;

    ViewLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}