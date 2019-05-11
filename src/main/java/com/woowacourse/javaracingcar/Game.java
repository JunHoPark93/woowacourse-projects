package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.dto.CarDto;
import com.woowacourse.javaracingcar.util.RacingCarUtil;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;

import java.util.List;

public class Game {
    private List<Car> cars;
    private NumberGenerator numberGenerator;

    public Game(NumberGenerator generator, List<Car> cars) {
        numberGenerator = generator;
        this.cars = cars;
    }

    public List<CarDto> play() {
        // 게임 루프: 자동차 한 대 씩 랜덤 숫자를 넘겨준다.
        for (Car car : cars) {
            car.attemptMove(numberGenerator.generateNumber());
        }

        return RacingCarUtil.convertCarToCarDto(cars);
    }

    public GameResult getGameResult() {
        return new GameResult(cars);
    }
}
