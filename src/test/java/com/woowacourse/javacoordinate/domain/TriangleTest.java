package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class TriangleTest {
    private Points points;

    @BeforeEach
    void setUp() {
        points = new Points(Arrays.asList(new Point(10, 10),
                new Point(14, 15), new Point(20, 8)));
    }

    @Test
    void 삼각형_초기화() {
        assertThatCode(() -> new Triangle(points)).doesNotThrowAnyException();
    }

    @Test
    void 삼각형_초기화_오류() {
        points = new Points(Arrays.asList(new Point(10, 10),
                new Point(14, 15)));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Triangle(points);
        }).withMessage("삼각형은 3개의 Point가 필요합니다");
    }

    @Test
    void 삼각형_면적_구하기() {
        Figure triangle = new Triangle(points);

        assertThat(triangle.calculateArea()).isEqualTo(29.0, offset(0.99));
    }

    @Test
    void 삼각형_둘레_구하기() {
        Figure triangle = new Triangle(points);

        assertThat(triangle.calculateLength()).isEqualTo(25.8, offset(0.99));
    }
}
