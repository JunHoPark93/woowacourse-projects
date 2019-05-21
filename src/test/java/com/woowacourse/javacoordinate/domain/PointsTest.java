package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PointsTest {
    @Test
    void 포인트_초기화() {
        Points points = new Points(Arrays.asList(new Point(3, 3), new Point(5, 5)));

        assertThat(points.getPoints()).isEqualTo(Arrays.asList(new Point(3, 3), new Point(5, 5)));
    }

    @Test
    void 포인트_중복처리() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Points(Arrays.asList(new Point(3, 3)
                    , new Point(5, 5), new Point(3, 3)));
        }).withMessage("중복된 좌표는 입력할 수 없습니다");
    }
}
