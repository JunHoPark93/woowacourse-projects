package webserver.view;

public final class View {
    private final String name;
    private final ViewLocation viewLocation;

    public View(String name, ViewLocation viewLocation) {
        this.name = name;
        this.viewLocation = viewLocation;
    }

    public String getName() {
        return name;
    }

    String getViewLocation() {
        return viewLocation.getLocation();
    }

    String getFullPath() {
        return "./" + viewLocation.getLocation() + "/" + name;
    }
}
