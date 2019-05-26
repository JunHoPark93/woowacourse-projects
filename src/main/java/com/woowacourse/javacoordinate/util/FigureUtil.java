package com.woowacourse.javacoordinate.util;

import com.woowacourse.javacoordinate.domain.*;

public class FigureUtil {
    private static final int LENGTH_CAL_CODE = 1;
    private static final int AREA_CAL_CODE = 2;
    private static final String LENGTH = "길이";
    private static final String AREA = "넓이";

    public static Result createResult(Figure figure, int calculateCode) {
        if (calculateCode == LENGTH_CAL_CODE) {
            return new Result(figure.getName(), LENGTH, figure.calculateLength());
        }
        if (calculateCode == AREA_CAL_CODE) {
            return new Result(figure.getName(), AREA, figure.calculateArea());
        }
        throw new IllegalArgumentException("잘못된 호출입니다");
    }
}
