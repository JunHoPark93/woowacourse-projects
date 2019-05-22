package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class LineTest {
    @Test
    void 두_점_사이의_거리_계산() {
        Point point1 = new Point(10,10);
        Point point2 = new Point(14,15);

        Points points = new Points(Arrays.asList(point1,point2));

        Figure line = new Line(points);

        assertThat(line.calculateLength()).isEqualTo(6.403124, offset(0.00099));
    }

    @Test
    void 두_점_사이의_넓이_계산() {
        Point point1 = new Point(10,10);
        Point point2 = new Point(14,15);

        Points points = new Points(Arrays.asList(point1,point2));

        Figure line = new Line(points);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(line::calculateArea)
                .withMessage("선은 넓이가 존재하지 않습니다.");
    }
}
