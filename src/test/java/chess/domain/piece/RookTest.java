package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(PieceColor.WHITE);
    }

    @Test
    void 초기화() {
        assertTrue(rook.isAlive());
    }

    @Test
    void 룩_이동_위_오른쪽() {
        Set<Square> movableSquares = rook.movableList(new Square(new XPosition("a"), new YPosition("1")));
        Set<Square> expectedSquares = new HashSet<>();
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("2")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("4")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("5")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("6")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("7")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("8")));

        expectedSquares.add(new Square(new XPosition("b"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("d"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("e"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("f"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("g"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("h"), new YPosition("1")));

        assertThat(movableSquares).isEqualTo(expectedSquares);
    }

    @Test
    void 룩_이동_사방() {
        Set<Square> movableSquares = rook.movableList(new Square(new XPosition("c"), new YPosition("3")));
        Set<Square> expectedSquares = new HashSet<>();
        //위
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("4")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("5")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("6")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("7")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("8")));

        //아래
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("2")));

        //왼쪽
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("b"), new YPosition("3")));


        //오른쪽
        expectedSquares.add(new Square(new XPosition("d"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("e"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("f"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("g"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("h"), new YPosition("3")));

        assertThat(movableSquares).isEqualTo(expectedSquares);
    }
}
