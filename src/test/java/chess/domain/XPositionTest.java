package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XPositionTest {
    @Test
    void x축_포지션_초기화() {
        assertThatCode(() -> {
            XPosition xPosition = new XPosition("h");
        }).doesNotThrowAnyException();
    }

    @Test
    void x축_포지션_비정상_초기화() {
        assertThrows(IllegalArgumentException.class, () -> {
            XPosition xPosition = new XPosition("i");
        });
    }
}
