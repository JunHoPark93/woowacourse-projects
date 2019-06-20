package chess.domain.piece;

import chess.domain.board.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTest {
    private Piece pawn;

    @BeforeEach
    void setUp() {
        pawn = new Pawn(PieceColor.WHITE);
    }

    @Test
    void 움직임_여부() {
        assertFalse(pawn.isMoved());
    }

    @Test
    void 움직일_수_있는_리스트_반환() {
        Set<Vector> movableVector = pawn.movableList(new Square(new XPosition("a"), new YPosition("2")));
        Set<Vector> expectedVector = new HashSet<>();
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("3")), Direction.UP));
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("4")), Direction.UP));
        expectedVector.add(new Vector(new Square(new XPosition("b"), new YPosition("3")), Direction.UP_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }
}
