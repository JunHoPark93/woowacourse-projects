package com.woowacourse.laddergame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    private int currentIdx = 3;
    @Test
    public void 왼쪽이동() {
        assertThat(Position.LEFT.move(currentIdx)).isEqualTo(currentIdx - 1);
    }

    @Test
    public void 오른쪽이동() {
        assertThat(Position.RIGHT.move(currentIdx)).isEqualTo(currentIdx + 1);
    }

    @Test
    public void 정지() {
        assertThat(Position.NONE.move(currentIdx)).isEqualTo(currentIdx);
    }
}