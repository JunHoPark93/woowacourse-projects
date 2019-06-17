package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YPositionTest {
    @Test
    void y축_포지션_초기화() {
        assertThatCode(() -> new YPosition("8")).doesNotThrowAnyException();
    }

    @Test
    void y축_포지션_비정상_초기화() {
        assertThrows(IllegalArgumentException.class, () -> new YPosition("9"));
    }

    @Test
    void 위로_이동() {
        YPosition position = new YPosition("1");
        assertThat(position.moveUp(1)).isEqualTo(new YPosition("2"));
    }

    @Test
    void 위로_이동_2칸() {
        YPosition position = new YPosition("1");
        assertThat(position.moveUp(2)).isEqualTo(new YPosition("3"));
    }

    @Test
    void 위로_이동_경계값() {
        YPosition position = new YPosition("8");
        assertThat(position.moveUp(1)).isEqualTo(new YPosition("8"));
    }

    @Test
    void 아래로_이동() {
        YPosition position = new YPosition("2");
        assertThat(position.moveDown(1)).isEqualTo(new YPosition("1"));
    }

    @Test
    void 아래로_이동_경계값() {
        YPosition position = new YPosition("1");
        assertThat(position.moveDown(1)).isEqualTo(new YPosition("1"));
    }
}
