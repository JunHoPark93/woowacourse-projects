package webserver.view;

public class ViewResolvers {
    public static final ViewResolver STATIC_RESOLVER = new StaticResourceResolver();
    public static final ViewResolver TEMPLATE_RESOLVER = new TemplateResourceResolver();
    public static final ViewResolver HANDLEBAR_RESOLVER = new HandlebarResourceResolver();
}
