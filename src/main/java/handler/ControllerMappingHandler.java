package handler;

import controller.Controller;
import model.http.HttpRequest;
import model.http.HttpResponse;
import model.http.ModelAndView;
import model.http.View;
import utils.HandlerMapper;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappingHandler implements Handler {
    private Controller controller;

    @Override
    public ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (controller != null) {
            // 실제 controller 를 호출하는 부분
            controller.service(httpRequest, httpResponse);

            View view = new View(httpResponse.getLocation());
            Map<String, Object> map = new HashMap<>();

            // TODO 리턴 데이터가 있으면 map 에 담아줄 것
            return new ModelAndView(view, map, false);
        }
        throw new RuntimeException("controller not initialized");
    }

    @Override
    public Handler getHandler(String path) {
        Controller target = HandlerMapper.findController(path);
        if (target != null) {
            this.controller = target;
            return this;
        }
        return null;
    }
}
