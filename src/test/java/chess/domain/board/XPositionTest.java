package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XPositionTest {
    @Test
    void x축_포지션_초기화() {
        assertThatCode(() -> new XPosition("h")).doesNotThrowAnyException();
    }

    @Test
    void x축_포지션_비정상_초기화() {
        assertThrows(IllegalArgumentException.class, () -> new XPosition("i"));
    }

    @Test
    void 좌로_이동() {
        XPosition position = new XPosition("c");
        assertThat(position.moveLeft(1)).isEqualTo(new XPosition("b"));
    }

    @Test
    void 좌로_이동_2칸() {
        XPosition position = new XPosition("c");
        assertThat(position.moveLeft(2)).isEqualTo(new XPosition("a"));
    }

    @Test
    void 좌로_이동_경계값() {
        XPosition xPosition = new XPosition("a");
        assertThat(xPosition.moveLeft(1)).isEqualTo(new XPosition("a"));
    }

    @Test
    void 우로_이동() {
        XPosition xPosition = new XPosition("a");
        XPosition position = xPosition.moveRight(1);
        assertThat(position).isEqualTo(new XPosition("b"));
    }

    @Test
    void 우로_이동_경계값() {
        XPosition position = new XPosition("h");
        assertThat(position.moveRight(1)).isEqualTo(new XPosition("h"));
    }
}
