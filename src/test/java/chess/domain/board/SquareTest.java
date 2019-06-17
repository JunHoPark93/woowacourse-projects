package chess.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {
    private Square square;

    @BeforeEach
    void setUp() {
        square = new Square(new XPosition("c"), new YPosition("2"));
    }

    @Test
    void 포지션_초기화() {
        assertThat(square).isEqualTo(new Square(new XPosition("c"), new YPosition("2")));
    }

    @Test
    void 좌로_이동() {
        Square moved = square.moveLeft(1);
        assertThat(moved).isEqualTo(new Square(new XPosition("b"), new YPosition("2")));
    }

    @Test
    void 우로_이동() {
        Square moved = square.moveRight(1);
        assertThat(moved).isEqualTo(new Square(new XPosition("d"), new YPosition("2")));
    }

    @Test
    void 위로_이동() {
        Square moved = square.moveUp(1);
        assertThat(moved).isEqualTo(new Square(new XPosition("c"), new YPosition("3")));
    }

    @Test
    void 아래로_이동() {
        Square moved = square.moveDown(1);
        assertThat(moved).isEqualTo(new Square(new XPosition("c"), new YPosition("1")));
    }
}
