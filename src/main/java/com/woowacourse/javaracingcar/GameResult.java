package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;

import java.util.*;
import java.util.stream.Collectors;

public class GameResult {
    private List<Car> cars;
    private int maxPosition;

    public GameResult(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("자동차 리스트는 비어 있을 수 없습니다");
        }
        this.cars = cars;
        calculateMaxPosition();
    }

    private void calculateMaxPosition() {
        maxPosition = cars.stream()
                .max(Comparator.comparing(Car::getPosition))
                .map(Car::getPosition)
                .orElse(0);
    }

    public List<Car> getWinnerCars() {
        // 아무도 출발하지 못한 경우
        if (maxPosition == 0) {
            return Collections.emptyList();
        }

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    public boolean isNobodyMovedForward() {
        return maxPosition == 0;
    }
}
