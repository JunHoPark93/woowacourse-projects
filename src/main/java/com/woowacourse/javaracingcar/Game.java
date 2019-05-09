package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.dto.CarDto;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private static final int STOP_BOUND = 3;
    private static final int MOVE_BOUND = 4;
    private List<Car> cars;
    private NumberGenerator numberGenerator;

    public Game(NumberGenerator generator, List<Car> cars) {
        numberGenerator = generator;
        this.cars = cars;
    }

    public List<CarDto> play() {
        // 게임 루프
        for (Car c : cars)  {
            c.moveForward(calculateMovingPosition(numberGenerator.generateNumber()));
        }

        return convertCarToCarDto(cars);
    }

    public List<CarDto> getWinners() {
        // 우승자 선정
        int max = calculateMaxPosition();
        if (max == 0) {
            return Collections.emptyList();
        }

        List<Car> winners = new ArrayList<>();
        cars.stream().filter(car -> car.getPosition() == max)
                .map(winners::add)
                .collect(Collectors.toList());

        return convertCarToCarDto(winners);
    }

    private List<CarDto> convertCarToCarDto(List<Car> cars) {
        List<CarDto> list = new ArrayList<>();
        for (Car c : cars) {
            list.add(new CarDto(c.getName(), c.getPosition()));
        }
        return list;
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

    private int calculateMaxPosition() {
        return cars.stream()
                .max(Comparator.comparing(Car::getPosition))
                .map(Car::getPosition)
                .orElseThrow(NoSuchElementException::new);
    }
}
