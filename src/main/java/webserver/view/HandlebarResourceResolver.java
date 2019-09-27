package webserver.view;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import webserver.exception.ResourceLoadException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HandlebarResourceResolver implements ViewResolver {
    private TemplateLoader loader = new ClassPathTemplateLoader();

    @Override
    public ViewResolveResult resolve(String path) {
        return resolve(path, new HashMap<>());
    }

    @Override
    public ViewResolveResult resolve(String path, Map<String, Object> model) {
        Template template = setTemplate(path);
        String resultResource = applyTemplate(model, template);

        return new ViewResolveResult(resultResource.getBytes(), path);
    }

    private Template setTemplate(String path) {
        loader.setPrefix("/" + ViewLocation.TEMPLATE.getLocation());
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        return compileTemplate(path, handlebars);
    }

    private String applyTemplate(Map<String, Object> model, Template template) {
        try {
            return template.apply(model);
        } catch (IOException e) {
            throw new ResourceLoadException("handler 에 model 을 적용할 수 없습니다");
        }
    }

    private Template compileTemplate(String path, Handlebars handlebars) {
        try {
            return handlebars.compile(path.substring(0, path.lastIndexOf(".")));
        } catch (IOException e) {
            throw new ResourceLoadException("handler 를 컴파일 할 수 없습니다");
        }
    }
}
