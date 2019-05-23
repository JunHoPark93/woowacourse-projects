package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.*;

public class FigureFactory {
    private static final int LINE_VERTEX = 2;
    private static final int TRIANGLE_VERTEX = 3;
    private static final int RECTANGLE_VERTEX = 4;

    public static Figure createFigure(Points points) {
        int size = points.getSize();

        if (size == LINE_VERTEX) {
            return new Line(points);
        }
        if (size == TRIANGLE_VERTEX) {
            return new Triangle(points);
        }
        if (size == RECTANGLE_VERTEX) {
            return new Rectangle(points);
        }
        throw new IllegalArgumentException("Points 형식이 잘못 되었습니다");
    }
}
