package com.woowacourse.lotto;

import com.woowacourse.lotto.controller.*;
import com.woowacourse.lotto.db.ConnectionFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", MainController::init);

        get("/history", HistoryController::init);

        get("/play", PlayController::init);

        post("/lotto", LottoController::init);

        post("/winning", DrawController::init);

        get("/throwexception", (request, response) -> {
            throw new Exception();
        });

        exception(Exception.class, (e, request, response) -> {
            response.status(404);
            response.body("잘못된 입력입니다.");
            try {
                // 예외가 발생할 경우 롤백 후 커넥션을 종료 시킨다.
                Connection con = ConnectionFactory.getConnection();
                if (!con.isClosed()) {
                    con.rollback();
                    con.close();
                }
            } catch (SQLException e1) {
                System.out.println("roll back!!");
                e1.printStackTrace();
            }
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
