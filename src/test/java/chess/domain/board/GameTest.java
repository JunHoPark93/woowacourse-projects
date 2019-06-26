package chess.domain.board;

import chess.domain.path.PathFactory;
import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    @Test
    void 실제_보드_초기화() {
        Player whitePlayer = new DefaultPlayer(PlayerFactory.init(PieceColor.WHITE));
        Player blackPlayer = new DefaultPlayer(PlayerFactory.init(PieceColor.BLACK));

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.whitePiecesCount()).isEqualTo(16);
        assertThat(game.blackPiecesCount()).isEqualTo(16);
    }

    @Test
    void 해당_위치_말_확인() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.getPiece(new Square(new XPosition("a"), new YPosition("8")))).isEqualTo(rook);
    }

    @Test
    void 룩_움직임_경우의수() {
        Piece rook = Rook.createBlack();
        Piece rook2 = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("7")), rook);
        black.put(new Square(new XPosition("f"), new YPosition("7")), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("b"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Set<Vector> moveList = game.moveList(new Square(new XPosition("b"), new YPosition("7")));
        List<Vector> expectedVectorList = Arrays.asList(
                new Vector(new Square(new XPosition("a"), new YPosition("7")), Direction.LEFT),
                new Vector(new Square(new XPosition("c"), new YPosition("7")), Direction.RIGHT),
                new Vector(new Square(new XPosition("d"), new YPosition("7")), Direction.RIGHT),
                new Vector(new Square(new XPosition("e"), new YPosition("7")), Direction.RIGHT),
                new Vector(new Square(new XPosition("b"), new YPosition("8")), Direction.UP),
                new Vector(new Square(new XPosition("b"), new YPosition("6")), Direction.DOWN),
                new Vector(new Square(new XPosition("b"), new YPosition("5")), Direction.DOWN),
                new Vector(new Square(new XPosition("b"), new YPosition("4")), Direction.DOWN),
                new Vector(new Square(new XPosition("b"), new YPosition("3")), Direction.DOWN),
                new Vector(new Square(new XPosition("b"), new YPosition("2")), Direction.DOWN));

        Set<Vector> expectedVector = new HashSet<>(expectedVectorList);
        assertThat(moveList).isEqualTo(expectedVector);
    }

    @Test
    void 나이트_움직임_경우의수() {
        Piece knight = Knight.createBlack();
        Piece rook = Rook.createBlack();
        Piece rook2 = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("8")), knight);
        black.put(new Square(new XPosition("a"), new YPosition("6")), rook);
        // path
        black.put(new Square(new XPosition("b"), new YPosition("7")), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("c"), new YPosition("6")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Set<Vector> moveList = game.moveList(new Square(new XPosition("b"), new YPosition("8")));
        List<Vector> expectedVectorList = Arrays.asList(
                new Vector(new Square(new XPosition("c"), new YPosition("6")), Direction.NONE),
                new Vector(new Square(new XPosition("d"), new YPosition("7")), Direction.NONE));

        Set<Vector> expectedVector = new HashSet<>(expectedVectorList);
        assertThat(moveList).isEqualTo(expectedVector);
    }

    @Test
    void 비숍_움직임_경우의수() {
        Piece bishop = Bishop.createBlack();
        Piece rook2 = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("f"), new YPosition("6")), bishop);
        black.put(new Square(new XPosition("g"), new YPosition("7")), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("d"), new YPosition("4")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Set<Vector> moveList = game.moveList(new Square(new XPosition("f"), new YPosition("6")));
        Set<Vector> expected = new HashSet<>();

        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("5")), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("4")), Direction.DOWN_RIGHT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("5")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("4")), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("7")), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("8")), Direction.UP_LEFT));

        assertThat(moveList).isEqualTo(expected);
    }

    @Test
    void 퀸_움직임_경우의수() {
        Piece queen = Queen.createBlack();
        Piece rook2 = Rook.createBlack();
        Piece rook3 = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("f"), new YPosition("6")), queen);
        black.put(new Square(new XPosition("g"), new YPosition("7")), rook2);
        black.put(new Square(new XPosition("f"), new YPosition("2")), rook3);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Piece pawn2 = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("d"), new YPosition("4")), pawn);
        white.put(new Square(new XPosition("b"), new YPosition("6")), pawn2);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Set<Vector> moveList = game.moveList(new Square(new XPosition("f"), new YPosition("6")));
        Set<Vector> expected = new HashSet<>();

        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("5")), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("4")), Direction.DOWN_RIGHT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("5")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("4")), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("7")), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("8")), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("7")), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("8")), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("7")), Direction.UP));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("8")), Direction.UP));

        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("6")), Direction.RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("6")), Direction.RIGHT));

        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("5")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("4")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("3")), Direction.DOWN));

        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("6")), Direction.LEFT));

        assertThat(moveList).isEqualTo(expected);
    }

    @Test
    void 킹_움직임_경우의수() {
        Piece king = King.createBlack();
        Piece rook2 = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("2")), king);
        black.put(new Square(new XPosition("b"), new YPosition("1")), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece king2 = King.createWhite();
        Piece pawn2 = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("d"), new YPosition("2")), king2);
        white.put(new Square(new XPosition("b"), new YPosition("3")), pawn2);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Set<Vector> moveList = game.moveList(new Square(new XPosition("b"), new YPosition("2")));
        Set<Vector> expected = new HashSet<>();

        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("1")), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("3")), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("3")), Direction.UP));

        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("2")), Direction.LEFT));

        assertThat(moveList).isEqualTo(expected);
    }

    @Test
    void 룩_움직임() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("7")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("b"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new XPosition("b"), new YPosition("7")),
                new Square(new XPosition("b"), new YPosition("3")));

        Piece expected = game.getPiece(new Square(new XPosition("b"), new YPosition("3")));
        assertThat(rook).isEqualTo(expected);
    }

    @Test
    void 보드_룩이_폰을잡는다() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);
        game.move(new Square(new XPosition("a"), new YPosition("8")),
                new Square(new XPosition("a"), new YPosition("2")));

        assertThat(game.whitePiecesCount()).isEqualTo(0);
    }

    @Test
    void 보드_룩이_폰을잡는다_유효하지않은_타겟() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThrows(RuntimeException.class, () -> game.move(new Square(new XPosition("a"), new YPosition("8")),
                new Square(new XPosition("b"), new YPosition("2"))));
    }

    @Test
    void 점수계산() {
        Piece king = King.createBlack();
        Piece rook = Rook.createBlack();
        Piece pawn = Pawn.createBlack();
        Piece knight = Knight.createBlack();

        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), king);
        black.put(new Square(new XPosition("a"), new YPosition("7")), rook);
        black.put(new Square(new XPosition("a"), new YPosition("6")), pawn);
        black.put(new Square(new XPosition("a"), new YPosition("5")), knight);

        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn2 = Pawn.createWhite();
        Piece king2 = King.createWhite();
        Piece queen = Queen.createWhite();
        Piece bishop = Bishop.createWhite();

        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), king2);
        white.put(new Square(new XPosition("b"), new YPosition("3")), pawn2);
        white.put(new Square(new XPosition("c"), new YPosition("4")), queen);
        white.put(new Square(new XPosition("d"), new YPosition("5")), bishop);

        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.score().getBlackScore()).isEqualTo(8.5);
        assertThat(game.score().getWhiteScore()).isEqualTo(13);
    }

    @Test
    void 폰_공격() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("4")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new XPosition("b"), new YPosition("4")),
                new Square(new XPosition("b"), new YPosition("3")));

        boolean isValid = game.move(new Square(new XPosition("a"), new YPosition("2")),
                new Square(new XPosition("b"), new YPosition("3")));

        assertTrue(isValid);
    }

    @Test
    void 폰_공격_불가() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("5")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new XPosition("b"), new YPosition("5")),
                new Square(new XPosition("b"), new YPosition("4")));

        assertThrows(RuntimeException.class, () -> game.move(new Square(new XPosition("a"), new YPosition("2")),
                new Square(new XPosition("b"), new YPosition("3"))));
    }

    @Test
    void 폰_이동_불가() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("5")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new XPosition("a"), new YPosition("5")),
                new Square(new XPosition("a"), new YPosition("3")));

        assertThrows(RuntimeException.class, () -> game.move(new Square(new XPosition("a"), new YPosition("2")),
                new Square(new XPosition("a"), new YPosition("3"))));
    }

    @Test
    void 킹_사망() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("4")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece king = King.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("b"), new YPosition("2")), king);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);
        boolean isContinue = game.move(new Square(new XPosition("b"), new YPosition("4")),
                new Square(new XPosition("b"), new YPosition("2")));

        assertFalse(isContinue);
    }

    @Test
    void 킹_사망하지_않음() {
        Piece rook = Rook.createBlack();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("4")), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece king = King.createWhite();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("b"), new YPosition("2")), king);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);
        boolean isContinue = game.move(new Square(new XPosition("b"), new YPosition("4")),
                new Square(new XPosition("b"), new YPosition("3")));

        assertTrue(isContinue);
    }

    @Test
    void 폰_세로점수확인() {
        Piece pawn1 = Pawn.createBlack();
        Piece pawn2 = Pawn.createBlack();
        Piece pawn3 = Pawn.createBlack();
        Piece pawn4 = Pawn.createBlack();

        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), pawn1);
        black.put(new Square(new XPosition("a"), new YPosition("7")), pawn2);
        black.put(new Square(new XPosition("a"), new YPosition("6")), pawn3);
        black.put(new Square(new XPosition("b"), new YPosition("5")), pawn4);

        Player blackPlayer = new DefaultPlayer(black);

        Piece wPawn1 = Pawn.createWhite();
        Piece wPawn2 = Pawn.createWhite();
        Piece wPawn3 = Pawn.createWhite();
        Piece wPawn4 = Pawn.createWhite();

        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), wPawn1);
        white.put(new Square(new XPosition("b"), new YPosition("3")), wPawn2);
        white.put(new Square(new XPosition("c"), new YPosition("4")), wPawn3);
        white.put(new Square(new XPosition("d"), new YPosition("5")), wPawn4);

        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.score().getBlackScore()).isEqualTo(2.5);
        assertThat(game.score().getWhiteScore()).isEqualTo(4);
    }
}
