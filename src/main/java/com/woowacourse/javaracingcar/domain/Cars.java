package com.woowacourse.javaracingcar.domain;

import java.util.*;

public class Cars {
    private Set<Car> cars = new LinkedHashSet<>();

    public Cars(List<String> carNames) {
        for (String name : carNames) {
            cars.add(new Car(name));
        }

        if (!isDuplicateNames(carNames)) {
            throw new IllegalArgumentException("자동차 이름들은 중복될 수 없습니다");
        }
    }

    private boolean isDuplicateNames(List<String> carNames) {
        return cars.size() == carNames.size();
    }

    public List<Car> getCarList() {
        return new ArrayList<>(cars);
    }
}
