package chess;

import chess.domain.board.*;
import chess.domain.piece.PieceColor;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    private static Game game;

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            // TODO 현재 라운드 가져오기

            // 끝나지 않은 라운드 확인

            // 끝나지 않았으면 판 초기화 해서 return

            // 끝났으면 새로 판 만들어서 insert 후 return
            Player whitePlayer = new DefaultPlayer(PlayerFactory.init(PieceColor.WHITE));
            Player blackPlayer = new DefaultPlayer(PlayerFactory.init(PieceColor.BLACK));

            game = new Game(whitePlayer, blackPlayer);

            // TODO dto 정의
//            List<PieceDto> list = new ArrayList();
//            model.put("list", list);

            return render(model, "index.html");
        });

        post("/movableList", (req, res) -> {
            String src = req.queryParams("src");
            //String trg = req.queryParams("trg");

            Square square = new Square(new XPosition(src.substring(0, 1)),
                    new YPosition(src.substring(1, 2)));

            Set<Vector> set = game.moveList(square);

            Map<String, Object> model = new HashMap<>();
            model.put("set", set);
            return render(model, "play.html");
        });

        post("/move", (req, res) -> {
            String src = req.queryParams("src");
            String trg = req.queryParams("trg");

            Square source = new Square(new XPosition(src.substring(0, 1)),
                    new YPosition(src.substring(1, 2)));

            Square target = new Square(new XPosition(trg.substring(0, 1)),
                    new YPosition(trg.substring(1, 2)));

            Map<String, Object> model = new HashMap<>();

            boolean playing = game.move(source, target);

            if (!playing) { // 왕이 죽은 경우
                model.put("turn", game.getTurn());
                return render(model, "result.html");
            }

            model.put("target", target);
            return render(model, "play.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
