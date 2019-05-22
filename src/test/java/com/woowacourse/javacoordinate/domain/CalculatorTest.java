package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class CalculatorTest {
    @Test
    void 두_점사이거리_계산() {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(14, 15);

        assertThat(Calculator.calculateLineDistance(point1, point2))
                .isEqualTo(6.403124, offset(0.00099));
    }

    @Test
    void 직사각형_계산() {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(22, 10);
        Point point3 = new Point(22, 18);
        Point point4 = new Point(10, 18);

        assertThat(Calculator.calculateRectangleArea(point1, point2, point3, point4)).isEqualTo(96);
    }

    @Test
    void 삼각형_계산() {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(14, 15);
        Point point3 = new Point(20, 8);

        assertThat(Calculator.calculateTriangleArea(point1, point2, point3)).isEqualTo(29.0, offset(0.99));
    }
}
