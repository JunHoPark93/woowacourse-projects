package com.woowacourse.javaracingcar.util;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.dto.CarDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCarUtil {
    private static final int MAX_NAME_LENGTH = 5;

    private RacingCarUtil() {
    }

    public static String[] splitIntoNames(String input) {
        String[] names = input.split(",");
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].trim();
            checkIfEmptyString(names[i]);
        }
        return names;
    }

    private static void checkIfEmptyString(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다");
        }
    }

    public static boolean isValidNameInput(String[] names) {
        checkIfDuplicateNames(names);
        for (String name : names) {
            checkIfNameIncludesSpace(name);
            checkIfValidNames(name);
        }

        return true;
    }

    private static void checkIfDuplicateNames(String[] names) {
        String[] lowerCaseNames = convertStringArrayToLowerCase(names);
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(lowerCaseNames));
        if (hashSet.size() != lowerCaseNames.length) {
            throw new IllegalArgumentException("중복된 이름을 입력할 수 없습니다");
        }
    }

    private static String[] convertStringArrayToLowerCase(String[] names) {
        IntStream.range(0, names.length).forEach(i -> names[i] = names[i].toLowerCase());
        return names;
    }

    private static void checkIfNameIncludesSpace(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException("이름에 공백을 포함할 수 없습니다");
        }
    }

    private static void checkIfValidNames(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5자를 넘을 수 없습니다");
        }
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
}
