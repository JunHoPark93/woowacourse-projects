package model.http;

public class View {
    private final String name;
    private final ViewLocation viewLocation;

    public View(String name, ViewLocation viewLocation) {
        this.name = name;
        this.viewLocation = viewLocation;
    }

    public String getName() {
        return name;
    }

    public String getViewLocation() {
        return viewLocation.getLocation();
    }

    public String getFullPath() {
        return "./" + viewLocation.getLocation() + "/" + name;
    }

}
