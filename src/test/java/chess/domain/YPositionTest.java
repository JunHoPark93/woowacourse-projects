package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YPositionTest {
    @Test
    void y축_포지션_초기화() {
        assertThatCode(() -> {
            YPosition yPosition = new YPosition("8");
        }).doesNotThrowAnyException();
    }

    @Test
    void y축_포지션_비정상_초기화() {
        assertThrows(IllegalArgumentException.class, () -> {
            YPosition yPosition = new YPosition("9");
        });
    }
}
