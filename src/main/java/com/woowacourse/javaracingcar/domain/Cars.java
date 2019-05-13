package com.woowacourse.javaracingcar.domain;

import com.woowacourse.javaracingcar.util.RacingCarUtil;

import java.util.*;

public class Cars {
    private Set<Car> cars = new LinkedHashSet<>();

    public Cars(List<String> carNames) {
        if (isDuplicateNames(carNames)) {
            throw new IllegalArgumentException("자동차 이름들은 중복될 수 없습니다");
        }
        for (String name : carNames) {
            cars.add(new Car(name));
        }
    }

    private boolean isDuplicateNames(List<String> carNames) {
        List<String> lowerCaseNames = RacingCarUtil.convertStringArrayToLowerCase(carNames);
        Set<String> nameSet = new HashSet<>(lowerCaseNames);

        return carNames.size() != nameSet.size();
    }

    public List<Car> getCarList() {
        return new ArrayList<>(cars);
    }
}
