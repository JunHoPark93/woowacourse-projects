package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {
    @Test
    void 포지션_초기화() {
        Square square = new Square(new XPosition("a"), new YPosition("1"));
        assertThat(square).isEqualTo(new Square(new XPosition("a"), new YPosition("1")));
    }
}
