package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;
import utils.HttpMethod;

public abstract class AbstractController implements Controller {
    @Override
    public void service(HttpRequest request, HttpResponse reponse) {
        // TODO get인지 post인지 구분해서 호출
        if (HttpMethod.GET.equals(request.getHttpMethod())) {
            doGet(request, reponse);
            return;
        }
        doPost(request, reponse);

        // TODO return 값이 ControllerReturn 타입 (ModelAndView, ResponseEntity) 인지 && instance of String 인지
    }

    protected abstract void doGet(HttpRequest request, HttpResponse response);
    protected abstract void doPost(HttpRequest request, HttpResponse response);
}
