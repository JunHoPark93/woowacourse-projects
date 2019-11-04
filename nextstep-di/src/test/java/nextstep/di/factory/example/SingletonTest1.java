package nextstep.di.factory.example;

import nextstep.annotation.Inject;
import nextstep.stereotype.Controller;

@Controller
public class SingletonTest1 {
    private MyQnaService qnaService;

    @Inject
    public SingletonTest1(MyQnaService qnaService) {
        this.qnaService = qnaService;
    }

    public MyQnaService getQnaService() {
        return qnaService;
    }
}
