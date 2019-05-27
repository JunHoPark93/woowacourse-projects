package com.woowacourse.javacoordinate.domain;

import com.woowacourse.javacoordinate.util.FigureFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class RectangleTest {
    private Points points;

    @BeforeEach
    void setUp() {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(22, 10);
        Point point3 = new Point(22, 18);
        Point point4 = new Point(10, 18);

        points = new Points(Arrays.asList(point1, point2, point3, point4));
    }

    @Test
    void 사각형_초기화() {
        assertThatCode(() -> new Rectangle(points)).doesNotThrowAnyException();
    }

    @Test
    void 사각형_초기화_오류() {
        points = new Points(Arrays.asList(new Point(10, 10),
                new Point(22, 10), new Point(22, 18)));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Rectangle(points))
                .withMessage("사각형은 4개의 Point가 필요합니다");
    }

    @Test
    void 사각형_초기화_오류_사다리꼴() {
        points = new Points(Arrays.asList(new Point(1, 1),
                new Point(2, 2), new Point(3, 2), new Point(4, 1)));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Rectangle(points))
                .withMessage("유효한 사각형이 아닙니다");
    }

    @Test
    void 사각형_초기화_오류_사다리꼴2() {
        points = new Points(Arrays.asList(new Point(1, 1),
                new Point(1, 2), new Point(2, 2), new Point(3, 1)));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Rectangle(points))
                .withMessage("유효한 사각형이 아닙니다");
    }

    @Test
    void 사각형_넓이() {
        Figure rectangle = FigureFactory.getFigure(points);

        assertThat(rectangle.calculateArea()).isEqualTo(96);
    }

    @Test
    void 사각형_둘레() {
        Figure rectangle = FigureFactory.getFigure(points);

        assertThat(rectangle.calculateLength()).isEqualTo(40);
    }
}
