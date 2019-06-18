package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    private Piece pawn;

    @BeforeEach
    void setUp() {
        pawn = new Pawn(PieceColor.WHITE);
    }

    @Test
    void 생성() {
        assertTrue(pawn.isAlive());
    }

    @Test
    void 움직임_여부() {
        assertFalse(pawn.isMoved());
    }

    @Test
    void 움직일_수_있는_리스트_반환() {
        Set<Square> movableSquares = pawn.movableList(new Square(new XPosition("a"), new YPosition("2")));
        Set<Square> expectedSquares = new HashSet<>();
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("3")));
        expectedSquares.add(new Square(new XPosition("a"), new YPosition("4")));
        expectedSquares.add(new Square(new XPosition("b"), new YPosition("3")));

        assertThat(expectedSquares).isEqualTo(movableSquares);
    }
}
