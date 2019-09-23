package webserver.handler.controller;

import webserver.http.HttpMethod;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public abstract class AbstractController implements Controller {
    protected ViewResolver viewResolver;

    public AbstractController(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        if (HttpMethod.GET.equals(request.getHttpMethod())) {
            doGet(request, response);
            return;
        }
        doPost(request, response);
    }

    /**
     * 이 메서드를 오버라이드 할 시 하위에 정의된 컨트롤러의 HttpRequest 의 HttpMethod 는 GET 이어야 하며
     * 이에 따른 처리는 service 함수가 하게 된다. 이것(super)을 직접 호출하는 방식은 잘못된 호출이다.
     *
     * @param request  HttpRequest
     * @param response HttpResponse
     */
    protected void doGet(HttpRequest request, HttpResponse response) throws Exception {
        response.sendError(HttpStatus.NOT_ALLOWED, "잘못된 doGet 메서드의 호출입니다");
    }

    /**
     * 이 메서드를 오버라이드 할 시 하위에 정의된 컨트롤러의 HttpRequest 의 HttpMethod 는 POST 이어야 하며
     * 이에 따른 처리는 service 함수가 하게 된다. 이것(super)을 직접 호출하는 방식은 잘못된 호출이다.
     *
     * @param request  HttpRequest
     * @param response HttpResponse
     */
    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {
        response.sendError(HttpStatus.NOT_ALLOWED, "잘못된 doGet 메서드의 호출입니다");
    }

    /**
     * 기본적으로 요청 path 과 동일한 mapping path 를 찾는다. 다른 mapping 전략을 사용하려면 상속하는 Controller 클래스에서
     * overriding 하여 사용하면 된다.
     *
     * @param request HttpRequest
     * @return boolean
     */
    @Override
    public boolean isMapping(HttpRequest request) {
        return getRequestedMapping().equals(request.getPath());
    }

    /**
     * AbstractController 를 상속하는 Controller 의 mapping path 주소를 반환하면 된다.
     *
     * @return String
     */
    protected abstract String getRequestedMapping();
}
