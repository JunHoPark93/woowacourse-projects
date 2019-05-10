package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.dto.CarDto;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int STOP_BOUND = 3;
    private static final int MOVE_BOUND = 4;

    private List<Car> cars;
    private NumberGenerator numberGenerator;
    private GameResult gameResult;

    public Game(NumberGenerator generator, List<Car> cars) {
        numberGenerator = generator;
        this.cars = cars;
    }

    public List<CarDto> play() {
        // 게임 루프: 자동차 한 대 씩 랜덤 숫자를 넘겨준다.
        for (Car c : cars) {
            c.moveForward(calculateMovingPosition(numberGenerator.generateNumber()));
        }

        return convertCarToCarDto(cars);
    }

    private int calculateMovingPosition(int generatedNumber) {
        if (generatedNumber >= MOVE_BOUND) {
            return 1;
        }
        if (generatedNumber <= STOP_BOUND) {
            return 0;
        }
        throw new IllegalArgumentException("올바르지 않은 인수: " + generatedNumber);
    }

    public List<CarDto> getWinners() {
        gameResult = new GameResult(cars);
        return convertCarToCarDto(gameResult.getWinnerCars());
    }

    private List<CarDto> convertCarToCarDto(List<Car> cars) {
        List<CarDto> list = new ArrayList<>();
        for (Car c : cars) {
            list.add(new CarDto(c.getName(), c.getPosition()));
        }
        return list;
    }
}
