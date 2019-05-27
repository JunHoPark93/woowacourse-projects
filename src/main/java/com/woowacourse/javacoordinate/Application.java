package com.woowacourse.javacoordinate;

import com.woowacourse.javacoordinate.domain.CoordinateSystem;
import com.woowacourse.javacoordinate.domain.Figure;
import com.woowacourse.javacoordinate.domain.Result;
import com.woowacourse.javacoordinate.service.CoordinateService;
import com.woowacourse.javacoordinate.util.ResultFactory;
import com.woowacourse.javacoordinate.view.InputView;
import com.woowacourse.javacoordinate.view.OutputView;

public class Application {

    public static void main(String[] args) {
        while (true) {
            Figure figure = InputView.inputCoordinatePoints();
            CoordinateSystem coordinateSystem = CoordinateService.makeCoordinateSystem(figure.getPoints());
            OutputView.printCoordinateSystem(coordinateSystem);

            int calculateCode = InputView.inputCalculateNo();
            Result result = ResultFactory.createResult(figure, calculateCode);

            OutputView.printResult(result);
        }
    }
}
