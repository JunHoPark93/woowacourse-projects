package handler;

import model.http.HttpRequest;
import model.http.HttpResponse;
import model.http.ModelAndView;
import model.http.View;

public class StaticResourceMappingHandler implements Handler {

    @Override
    public ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        // TODO 정적 자원 찾기
        View view = new View(httpRequest.getPath().substring(1));
        return new ModelAndView(view, true);
    }

    @Override
    public Handler getHandler(String filePath) {
        // TODO file 이 존재하는지 확인
        if ("/css/".equals(filePath) || "/js/".equals(filePath) || "/fonts/".equals(filePath) || "/images/".equals(filePath)) {
            return this;
        }
        return null;
    }
}
