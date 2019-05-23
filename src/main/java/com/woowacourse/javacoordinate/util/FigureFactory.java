package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FigureFactory {
    private final static Map<Integer, Function<Points, Figure>> map = new HashMap<>();

    static {
        map.put(2, Line::new);
        map.put(3, Triangle::new);
        map.put(4, Rectangle::new);
    }

    public static Figure getShape(Points points) {
        Function<Points, Figure> figureFunction = map.get(points.getSize());
        return figureFunction.apply(points);
    }
}
