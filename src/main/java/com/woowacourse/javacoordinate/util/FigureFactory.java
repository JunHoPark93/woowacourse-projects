package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.*;

import java.util.Arrays;
import java.util.function.Function;

public enum FigureFactory {
    LINE(2, Line::new),
    TRIANGLE(3, Triangle::new),
    RECTANGLE(4, Rectangle::new);

    int pointCount;
    Function<Points, Figure> function;

    FigureFactory(int pointCount, Function<Points, Figure> function) {
        this.pointCount = pointCount;
        this.function = function;
    }

    public static Figure getFigure(Points points) {
        int pointCount = points.getSize();

        FigureFactory figureFactory = Arrays.stream(FigureFactory.values())
                .filter(shape -> shape.pointCount == pointCount)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return figureFactory.function.apply(points);
    }
}
