package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PointTest {
    @Test
    void 한_점_정상입력() {
        int x = 0;
        int y = 0;

        assertThatCode(() -> new Point(x, y)).doesNotThrowAnyException();
    }

    @Test
    void 한_점_비정상입력() {
        int x = 25;
        int y = 25;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Point(x,y)).withMessage("좌표 입력 범위를 넘어갔습니다");
    }

    @Test
    void 한_점_비정상입력_음수() {
        int x = -10;
        int y = -10;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Point(x,y)).withMessage("좌표 입력 범위를 넘어갔습니다");
    }
}
