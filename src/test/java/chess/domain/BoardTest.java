package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    void 보드생성() {
        Board board = new Board();
        assertThat(board).isEqualTo(new Board());
    }

    @Test
    void 포지션_초기화_확인() {
        Board board = new Board();
        assertThat(board.getSize()).isEqualTo(64);
    }
}
