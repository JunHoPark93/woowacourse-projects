package webserver.view;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HandlebarResourceResolver implements ViewResolver {
    private TemplateLoader loader = new ClassPathTemplateLoader();

    // TODO 중복제거 path와 model을 감싸는 객체를 만들어서 하나로 통일할까?
    @Override
    public ViewResolveResult resolve(String path) throws Exception {
        Template template = setTemplate(path);
        String resultResource = template.apply(new HashMap<>());

        return new ViewResolveResult(resultResource.getBytes(), path);
    }

    @Override
    public ViewResolveResult resolve(String path, Map<String, Object> model) throws Exception {
        Template template = setTemplate(path);
        String resultResource = template.apply(model);

        return new ViewResolveResult(resultResource.getBytes(), path);
    }

    private Template setTemplate(String path) throws IOException {
        loader.setPrefix("/" + ViewLocation.TEMPLATE.getLocation());
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        return handlebars.compile(path.substring(0, path.lastIndexOf(".")));
    }
}
