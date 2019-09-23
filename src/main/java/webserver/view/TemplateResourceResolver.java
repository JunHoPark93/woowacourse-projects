package webserver.view;

public class TemplateResourceResolver implements ViewResolver {
    @Override
    public String resolve(String path) {
        return ViewLocation.TEMPLATE.getLocation() + path;
    }
}
