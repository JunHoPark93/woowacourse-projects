package chess.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {
    private Square square;

    @BeforeEach
    void setUp() {
        square = new Square(new XPosition("a"), new YPosition("1"));
    }

    @Test
    void 포지션_초기화() {
        assertThat(square).isEqualTo(new Square(new XPosition("a"), new YPosition("1")));
    }
}
