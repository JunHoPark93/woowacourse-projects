/*
 * Class:   GameUtil
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-30
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javaracingcar.util;

import com.junhopark.javaracingcar.domain.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameUtil {
    private static final int MOVE_THRESHOLD = 4;
    private static final int RANDOM_NUMBER_RANGE = 9;

    public static List<Car> getCarList(String input) {
        String[] carNames = input.split(",");
        List<Car> carList = new ArrayList<>();
        for (String carName : carNames) {
            carList.add(new Car(carName));
        }
        return carList;
    }

    public static boolean isMoveCondition() {
        int randomInt = ThreadLocalRandom.current().nextInt(RANDOM_NUMBER_RANGE) + 1;
        return randomInt >= MOVE_THRESHOLD;
    }

    public static List<String> getNameListWithPosition(int position, List<Car> carList) {
        return carList.stream()
                .filter(car -> car.getPosition() == position)
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
