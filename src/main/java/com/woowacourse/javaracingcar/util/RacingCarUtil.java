package com.woowacourse.javaracingcar.util;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.dto.CarDto;

import java.util.*;
import java.util.stream.Collectors;

public class RacingCarUtil {
    private RacingCarUtil() {
    }

    public static List<String> convertStringArrayToLowerCase(List<String> names) {
        return names.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public static String joinCarNames(List<CarDto> cars) {
        return cars.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(", "));
    }

    public static List<CarDto> convertCarToCarDto(List<Car> cars) {
        List<CarDto> list = new ArrayList<>();
        for (Car c : cars) {
            list.add(new CarDto(c.getName(), c.getPosition()));
        }
        return list;
    }

    public static int convertTriesStringToInteger(String input) {
        return Integer.parseInt(input);
    }

    public static void checkValidTriesInput(int tries) {
        if (tries <= 0) {
            throw new IllegalArgumentException("1이상 입력이 필요합니다");
        }
    }
}
