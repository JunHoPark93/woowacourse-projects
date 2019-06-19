package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {
    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(PieceColor.WHITE);
    }

    @Test
    void 초기화() {
        assertTrue(knight.isAlive());
    }

    @Test
    void 나이트_이동_사방() {
        Set<Square> movableSquares = knight.movableList(new Square(new XPosition("d"), new YPosition("4")));
        Set<Square> expectedSquares = new HashSet<>();
        expectedSquares.add(new Square(new XPosition("e"), new YPosition("7")));
        expectedSquares.add(new Square(new XPosition("g"), new YPosition("5")));
        expectedSquares.add(new Square(new XPosition("g"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("e"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("1")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("5")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("7")));

        assertThat(movableSquares).isEqualTo(expectedSquares);
    }

    @Test
    void 나이트_이동_막힌곳() {
        Set<Square> movableSquares = knight.movableList(new Square(new XPosition("b"), new YPosition("1")));
        Set<Square> expectedSquares = new HashSet<>();
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("4")));
        expectedSquares.add(new Square(new XPosition("c"), new YPosition("4")));
        expectedSquares.add(new Square(new XPosition("e"), new YPosition("2")));

        assertThat(movableSquares).isEqualTo(expectedSquares);
    }
}
