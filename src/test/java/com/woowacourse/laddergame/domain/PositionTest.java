package com.woowacourse.laddergame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @Test
    public void 왼쪽이동() {
        int nowIndex = 3;
        assertThat(Position.LEFT.move(nowIndex)).isEqualTo(nowIndex - 1);
    }

    @Test
    public void 오른쪽이동() {
        int nowIndex = 3;
        assertThat(Position.RIGHT.move(nowIndex)).isEqualTo(nowIndex + 1);
    }

    @Test
    public void 정지() {
        int nowIndex = 3;
        assertThat(Position.NONE.move(nowIndex)).isEqualTo(nowIndex);
    }
}