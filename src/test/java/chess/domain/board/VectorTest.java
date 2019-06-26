package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VectorTest {
    @Test
    void 벡터_UP() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.UP;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("6")), Direction.UP));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("7")), Direction.UP));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("8")), Direction.UP));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_DOWN() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.DOWN;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("4")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("3")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("2")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("1")), Direction.DOWN));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_LEFT() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.LEFT;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("5")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("5")), Direction.LEFT));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_RIGHT() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.RIGHT;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("5")), Direction.RIGHT));
        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("5")), Direction.RIGHT));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("5")), Direction.RIGHT));
        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("5")), Direction.RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("5")), Direction.RIGHT));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_UP_RIGHT() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.UP_RIGHT;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("6")), Direction.UP_RIGHT));
        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("7")), Direction.UP_RIGHT));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("8")), Direction.UP_RIGHT));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_UP_LEFT() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.UP_LEFT;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("6")), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("7")), Direction.UP_LEFT));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_DOWN_RIGHT() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.DOWN_RIGHT;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("4")), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("3")), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("2")), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("1")), Direction.DOWN_RIGHT));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 벡터_DOWN_LEFT() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));
        Direction direction = Direction.DOWN_LEFT;
        Vector vector = new Vector(square, direction);

        Set<Vector> expected = new HashSet<>();
        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("4")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("3")), Direction.DOWN_LEFT));

        assertThat(vector.vectorList()).isEqualTo(expected);
    }

    @Test
    void 같은_Square() {
        Square square = new Square(new XPosition("c"), new YPosition("5"));

        assertThat(square).isEqualTo(new Square(new XPosition("c"), new YPosition("5")));
    }

    @Test
    void 같은_Square_포함() {
        Set<Vector> vectors = new HashSet<>();
        vectors.add(new Vector(new Square(new XPosition("c"), new YPosition("5")), Direction.UP));
        vectors.add(new Vector(new Square(new XPosition("c"), new YPosition("6")), Direction.UP));

        Vector vector = new Vector(new Square(new XPosition("c"), new YPosition("5")), Direction.UP);

        assertTrue(vector.containsSameSquare(vectors));
    }

    @Test
    void 같은_라인에_위치() {
        Vector vector = new Vector(new Square(new XPosition("c"), new YPosition("5")), Direction.UP);

        assertTrue(vector.isLocatedSameLine(new Square(new XPosition("d"), new YPosition("5"))));
    }

    @Test
    void Square_포함하는지() {
        Map<Square, Piece> pieces = new HashMap<>();
        pieces.put(new Square(new XPosition("c"), new YPosition("5")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("c"), new YPosition("6")), Pawn.createBlack());

        Vector vector = new Vector(new Square(new XPosition("c"), new YPosition("5")), Direction.UP);

        assertTrue(vector.containsSquare(pieces));
    }
}
