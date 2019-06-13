package com.woowacourse.lotto;

import com.woowacourse.lotto.controller.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", MainController::init);

        get("/history", HistoryController::init);

        get("/play", PlayController::init);

        post("/lotto", LottoController::init);

        post("/winning", WinningController::init);

        get("/throwexception", (request, response) -> {
            throw new Exception();
        });

        exception(Exception.class, (e, request, response) -> {
            response.status(404);
            response.body("리소스를 찾을 수 없습니다.");
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
