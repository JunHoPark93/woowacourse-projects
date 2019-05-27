package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CoordinateTest {
    @Test
    void 좌표_정상_초기화() {
        assertThatCode(() -> new Coordinate(1)).doesNotThrowAnyException();
    }

    @Test
    void 좌표_비정상_초기화() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Coordinate(-1))
                .withMessage("좌표 입력 범위를 넘어갔습니다");
    }
}