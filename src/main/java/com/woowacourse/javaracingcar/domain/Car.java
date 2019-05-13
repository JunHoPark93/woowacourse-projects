package com.woowacourse.javaracingcar.domain;

import com.woowacourse.javaracingcar.util.RacingCarUtil;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    public static final int MOVE_BOUND = 4;
    private static final String BLANK_SPACE = " ";

    private final String name;
    private int position;

    public Car(String name) {
        this(name, 0);
    }

    public Car(String name, int position) {
        isProperName(name);
        this.name = name;
        this.position = position;
    }

    private void isProperName(String name) {
        // 빈문자열, 5글자, 이름에 공백
        validateEmptyName(name);
        validateNameLength(name);
        validateNameContainsBlank(name);
    }

    private void validateEmptyName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("비어 있는 이름을 입력할 수 없습니다");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5글자를 넘을 수 없습니다");
        }
    }

    private void validateNameContainsBlank(String name) {
        if (name.contains(BLANK_SPACE)) {
            throw new IllegalArgumentException("이름은 공백을 포함할 수 없습니다");
        }
    }

    public void attemptMove(int number) {
        if (number >= MOVE_BOUND) {
            moveForward();
        }
    }

    private void moveForward() {
        this.position++;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (position != car.position) return false;
        return name != null ? name.equals(car.name) : car.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + position;
        return result;
    }
}
