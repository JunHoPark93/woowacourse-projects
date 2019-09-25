package webserver.view;

import utils.FileIoUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class StaticResourceResolver implements ViewResolver {
    @Override
    public ViewResolveResult resolve(String path) throws IOException, URISyntaxException {
        return new ViewResolveResult(
                FileIoUtils.loadFileFromClasspath(ViewLocation.STATIC.getLocation() + path),
                path);
    }

    @Override
    public ViewResolveResult resolve(String path, Map<String, Object> model) throws Exception {
        throw new RuntimeException("정적 resolver 는 model 을 내려줄 수 없습니다");
    }
}
