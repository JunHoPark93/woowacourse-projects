package webserver.view;

import utils.FileIoUtils;
import webserver.exception.ResourceNotFoundException;
import webserver.exception.ViewResolveException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class StaticResourceResolver implements ViewResolver {
    @Override
    public ViewResolveResult resolve(String path) {
        try {
            return new ViewResolveResult(
                    FileIoUtils.loadFileFromClasspath(ViewLocation.STATIC.getLocation() + path),
                    path);
        } catch (IOException | URISyntaxException e) {
            throw new ResourceNotFoundException("리소스를 찾을 수 없습니다");
        }
    }

    @Override
    public ViewResolveResult resolve(String path, Map<String, Object> model) {
        throw new ViewResolveException("정적 resolver 는 model 을 내려줄 수 없습니다");
    }
}
