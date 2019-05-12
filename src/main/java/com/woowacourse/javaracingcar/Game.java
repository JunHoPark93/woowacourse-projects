package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.domain.Cars;
import com.woowacourse.javaracingcar.dto.CarDto;
import com.woowacourse.javaracingcar.util.RacingCarUtil;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;

import java.util.List;

public class Game {
    private Cars cars;
    private NumberGenerator numberGenerator;
    private int tries;

    public Game(NumberGenerator generator, Cars cars, int tries) {
        numberGenerator = generator;
        this.cars = cars;
        this.tries = tries;
    }

    public List<CarDto> play() {
        attemptToMoveCars();
        return RacingCarUtil.convertCarToCarDto(cars.getCarList());
    }

    private void attemptToMoveCars() {
        for (Car car : cars.getCarList()) {
            car.attemptMove(numberGenerator.generateNumber());
        }
        tries--;
    }

    public GameResult getGameResult() {
        return new GameResult(cars.getCarList());
    }

    public boolean isEnd() {
        return tries == 0;
    }
}
