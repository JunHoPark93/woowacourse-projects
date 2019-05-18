package com.woowacourse.laddergame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PositionTest {
    private int currentIdx = 3;
    private int maxIdx = 4;

    @Test
    public void 왼쪽으로_이동하는경우() {
        assertThat(Position.LEFT.move(currentIdx, maxIdx)).isEqualTo(currentIdx - 1);
    }

    @Test
    public void 오른쪽으로_이동하는경우() {
        assertThat(Position.RIGHT.move(currentIdx, maxIdx)).isEqualTo(currentIdx + 1);
    }

    @Test
    public void 정지_하는경우() {
        assertThat(Position.NONE.move(currentIdx, maxIdx)).isEqualTo(currentIdx);
    }

    @Test
    void 이동_불가한경우_자연수가_아닌_인덱스() {
        int zeroIndex = 0;

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Position.LEFT.move(zeroIndex, maxIdx);
        }).withMessage("인덱스는 1부터 시작해야 합니다");
    }

    @Test
    void 이동_불가한경우_최대_인덱스를_넘는경우() {
        int currentIdx = 5;

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Position.LEFT.move(currentIdx, maxIdx);
        }).withMessage("최대 인덱스보다 큰 인덱스입니다");
    }
}