package com.woowacourse.javaracingcar.domain;

import com.woowacourse.javaracingcar.util.RacingCarUtil;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String BLANK_SPACE = " ";

    private final String name;
    private int position;

    public Car(String name) {
        this(name, 0);
    }

    public Car(String name, int position) {
        if (!isProperName(name)) {
            throw new IllegalArgumentException("자동차 이름이 적절하지 않습니다");
        }
        this.name = name;
        this.position = position;
    }

    private boolean isProperName(String name) {
        // 빈문자열, 5글자, 이름에 공백
        return name.length() != 0
                && name.length() <= MAX_NAME_LENGTH
                && !name.contains(BLANK_SPACE);
    }

    public void attemptMove(int number) {
        if (number >= RacingCarUtil.MOVE_BOUND) {
            moveForward();
        }
    }

    private void moveForward() {
        this.position ++;
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
