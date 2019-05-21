package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class CalculatorTest {
    @Test
    void 두_점사이거리_계산() {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(14, 15);

        assertThat(Calculator.getDistanceBetweenTwoPoints(point1, point2))
                .isEqualTo(6.403124, offset(0.00099));
    }
}
