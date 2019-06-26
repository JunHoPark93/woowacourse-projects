package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    Piece queen;

    @BeforeEach
    void setUp() {
        queen = Queen.createWhite();
    }

    @Test
    void movablelist() {
        Set<Vector> movableList = queen.movableList(new Square(new XPosition("f"), new YPosition("6")));
        Set<Vector> expected = new HashSet<>();

        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("7")), Direction.UP_RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("8")), Direction.UP_RIGHT));

        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("5")), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("4")), Direction.DOWN_RIGHT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("5")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("4")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("3")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("2")), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("1")), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("7")), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("8")), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("7")), Direction.UP));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("8")), Direction.UP));

        expected.add(new Vector(new Square(new XPosition("g"), new YPosition("6")), Direction.RIGHT));
        expected.add(new Vector(new Square(new XPosition("h"), new YPosition("6")), Direction.RIGHT));

        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("5")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("4")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("3")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("2")), Direction.DOWN));
        expected.add(new Vector(new Square(new XPosition("f"), new YPosition("1")), Direction.DOWN));

        expected.add(new Vector(new Square(new XPosition("a"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("b"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("c"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("d"), new YPosition("6")), Direction.LEFT));
        expected.add(new Vector(new Square(new XPosition("e"), new YPosition("6")), Direction.LEFT));

        assertThat(movableList).isEqualTo(expected);
    }
}
