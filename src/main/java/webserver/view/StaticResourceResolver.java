package webserver.view;

public class StaticResourceResolver implements ViewResolver {
    @Override
    public String resolve(String path) {
        return ViewLocation.STATIC.getLocation() + path;
    }
}
