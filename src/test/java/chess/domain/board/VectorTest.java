package chess.domain.board;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
}
