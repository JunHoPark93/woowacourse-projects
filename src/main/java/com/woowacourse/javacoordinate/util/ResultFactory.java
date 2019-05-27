package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.Figure;
import com.woowacourse.javacoordinate.domain.Result;

import java.util.Arrays;
import java.util.function.Function;

public enum ResultFactory {
    CAL_LENGTH(1, (Figure figure) -> new Result(figure.getName(), "길이", figure.calculateLength())),
    CAL_AREA(2, (Figure figure) -> new Result(figure.getName(), "넓이", figure.calculateArea()));

    private int calculateCode;
    private Function<Figure, Result> figureResultFunction;

    ResultFactory(int calculateCode, Function<Figure, Result> figureResultFunction) {
        this.calculateCode = calculateCode;
        this.figureResultFunction = figureResultFunction;
    }

    public static Result createResult(Figure figure, int calculateCode) {
        ResultFactory resultFactory = Arrays.stream(values())
                .filter(result -> result.calculateCode == calculateCode)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return resultFactory.figureResultFunction.apply(figure);
    }
}
