package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTest {
    private Piece wPawn;
    private Piece bPawn;

    @BeforeEach
    void setUp() {
        wPawn = Pawn.createWhite();
        bPawn = Pawn.createBlack();
    }

    @Test
    void 화이트_폰_이동가능영역() {
        Set<Vector> movableVector = wPawn.movableList(new Square(new XPosition("a"), new YPosition("2")));
        Set<Vector> expectedVector = new HashSet<>();
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("3")), Direction.UP));
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("4")), Direction.UP));
        expectedVector.add(new Vector(new Square(new XPosition("b"), new YPosition("3")), Direction.UP_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }

    @Test
    void 블랙_폰_이동가능영역() {
        Set<Vector> movableVector = bPawn.movableList(new Square(new XPosition("a"), new YPosition("7")));
        Set<Vector> expectedVector = new HashSet<>();
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("6")), Direction.DOWN));
        expectedVector.add(new Vector(new Square(new XPosition("b"), new YPosition("6")), Direction.DOWN_RIGHT));
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("5")), Direction.DOWN));

        assertThat(expectedVector).isEqualTo(movableVector);
    }

    @Test
    void 화이트_폰_7번행() {
        Set<Vector> movableVector = wPawn.movableList(new Square(new XPosition("a"), new YPosition("7")));
        Set<Vector> expectedVector = new HashSet<>();
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("8")), Direction.UP));
        expectedVector.add(new Vector(new Square(new XPosition("b"), new YPosition("8")), Direction.UP_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }

    @Test
    void 블랙_폰_2번행() {
        Set<Vector> movableVector = bPawn.movableList(new Square(new XPosition("a"), new YPosition("2")));
        Set<Vector> expectedVector = new HashSet<>();
        expectedVector.add(new Vector(new Square(new XPosition("a"), new YPosition("1")), Direction.DOWN));
        expectedVector.add(new Vector(new Square(new XPosition("b"), new YPosition("1")), Direction.DOWN_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }
}
