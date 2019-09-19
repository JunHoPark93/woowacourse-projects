package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;
import utils.HttpMethod;
import utils.HttpStatus;

public abstract class AbstractController implements Controller {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if (HttpMethod.GET.equals(request.getHttpMethod())) {
            doGet(request, response);
            return;
        }
        doPost(request, response);
    }

    /**
     * 이 메서드를 오버라이드 할 시 하위에 정의된 컨트롤러의 HttpRequest 의 HttpMethod 는 GET 이어야 하며
     * 이에 따른 처리는 service 함수가 하게 된다.
     *
     * @param request
     * @param response
     */
    protected void doGet(HttpRequest request, HttpResponse response) {
        if (request.getHttpMethod() != HttpMethod.GET) {
            response.sendError(HttpStatus.NOT_ALLOWED, "지원하지 않는 http method 형식입니다");
        }
        response.sendError(HttpStatus.FORBIDDEN, "잘못된 doGet 메서드의 호출입니다");
    }

    /**
     * 이 메서드를 오버라이드 할 시 하위에 정의된 컨트롤러의 HttpRequest 의 HttpMethod 는 POST 이어야 하며
     * 이에 따른 처리는 service 함수가 하게 된다.
     *
     * @param request
     * @param response
     */
    protected void doPost(HttpRequest request, HttpResponse response) {
        if (request.getHttpMethod() != HttpMethod.POST) {
            response.sendError(HttpStatus.NOT_ALLOWED, "지원하지 않는 http method 형식입니다");
        }
        response.sendError(HttpStatus.FORBIDDEN, "잘못된 doGet 메서드의 호출입니다");
    }
}
