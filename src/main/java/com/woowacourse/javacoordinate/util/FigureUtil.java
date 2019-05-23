package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.*;

public class FigureUtil {
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
