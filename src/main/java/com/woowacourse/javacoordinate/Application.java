package com.woowacourse.javacoordinate;

import com.woowacourse.javacoordinate.domain.Points;
import com.woowacourse.javacoordinate.view.InputView;

public class Application {

    public static void main(String[] args) {
        Points points = InputView.inputCoordinatePoints();
        System.out.println();
    }
}
