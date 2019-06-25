package chess;

import chess.controller.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", MainController::init);

        get("/init", InitController::init);

        post("/movableList", MovableController::init);

        post("/move", MoveController::init);

        get("/end", EndController::init);

        get("/status", StatusController::init);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
