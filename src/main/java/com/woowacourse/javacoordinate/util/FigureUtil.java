package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.Figure;
import com.woowacourse.javacoordinate.domain.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FigureUtil {
    private static final String LENGTH = "길이";
    private static final String AREA = "넓이";

    private final static Map<Integer, Function<Figure, Result>> map = new HashMap<>();

    static {
        map.put(1, (Figure figure) -> new Result(figure.getName(), LENGTH, figure.calculateLength()));
        map.put(2, (Figure figure) -> new Result(figure.getName(), AREA, figure.calculateArea()));
    }

    public static Result createResult(Figure figure, int calculateCode) {
        Function<Figure, Result> figureResultFunction = map.get(calculateCode);
        return figureResultFunction.apply(figure);
    }
}
