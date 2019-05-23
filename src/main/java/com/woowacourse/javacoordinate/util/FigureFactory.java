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

    public static Result createResult(Figure figure) {
        if (figure instanceof Line) {
            return calculateLine(figure);
        }
        if (figure instanceof Triangle) {
            return calculateTriangle(figure);
        }
        if (figure instanceof Rectangle) {
            return calculateRectangle(figure);
        }
        throw new IllegalArgumentException("Points 형식이 잘못 되었습니다");
    }

    private static Result calculateLine(Figure figure) {
        double result = figure.calculateLength();

        return new Result(result, "Line");
    }

    private static Result calculateTriangle(Figure figure) {
        double result = figure.calculateArea();

        return new Result(result, "Triangle");
    }

    private static Result calculateRectangle(Figure figure) {
        double result = figure.calculateArea();

        return new Result(result, "Rectangle");
    }
}
