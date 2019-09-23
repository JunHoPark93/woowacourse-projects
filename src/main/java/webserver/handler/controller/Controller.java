package webserver.handler.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public interface Controller {
    /**
     * 컨트롤러의 요청 처리 로직이다. 구현부에서 분기에 맞게 처리해주면 된다.
     * @param request HttpRequest
     * @param response HttpResponse
     */
    void service(HttpRequest request, HttpResponse response) throws Exception;

    /**
     * 각 컨트롤러가 요청에 맞는 컨트롤러인지 판단하는 로직을 구현하면 된다.
     * @param request HttpRequest
     * @return boolean
     */
    boolean isMapping(HttpRequest request);
}
