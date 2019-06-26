package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = Rook.createWhite();
    }

    @Test
    void 룩_이동_위_오른쪽2() {
        Set<Vector> movableSquares = rook.movableList(new Square(new XPosition("a"), new YPosition("1")));
        Set<Vector> expectedSquares = new HashSet<>();
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("2")), Direction.UP));
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("3")), Direction.UP));
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("4")), Direction.UP));
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("5")), Direction.UP));
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("6")), Direction.UP));
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("7")), Direction.UP));
        expectedSquares.add(new Vector(new Square(new XPosition("a"), new YPosition("8")), Direction.UP));

        expectedSquares.add(new Vector(new Square(new XPosition("b"), new YPosition("1")), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new XPosition("c"), new YPosition("1")), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new XPosition("d"), new YPosition("1")), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new XPosition("e"), new YPosition("1")), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new XPosition("f"), new YPosition("1")), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new XPosition("g"), new YPosition("1")), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new XPosition("h"), new YPosition("1")), Direction.RIGHT));

        assertThat(movableSquares).isEqualTo(expectedSquares);
    }

    @Test
    void 룩_이동_사방() {
        Set<Vector> movableVectors = rook.movableList(new Square(new XPosition("c"), new YPosition("3")));
        Set<Vector> expectedVectors = new HashSet<>();
        //위
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("4")), Direction.UP));
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("5")), Direction.UP));
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("6")), Direction.UP));
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("7")), Direction.UP));
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("8")), Direction.UP));

        //아래
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("1")), Direction.DOWN));
        expectedVectors.add(new Vector(new Square(new XPosition("c"), new YPosition("2")), Direction.DOWN));

        //왼쪽
        expectedVectors.add(new Vector(new Square(new XPosition("a"), new YPosition("3")), Direction.LEFT));
        expectedVectors.add(new Vector(new Square(new XPosition("b"), new YPosition("3")), Direction.LEFT));


        //오른쪽
        expectedVectors.add(new Vector(new Square(new XPosition("d"), new YPosition("3")), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new XPosition("e"), new YPosition("3")), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new XPosition("f"), new YPosition("3")), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new XPosition("g"), new YPosition("3")), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new XPosition("h"), new YPosition("3")), Direction.RIGHT));

        assertThat(movableVectors).isEqualTo(expectedVectors);
    }
}
