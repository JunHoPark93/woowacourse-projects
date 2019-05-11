package com.woowacourse.javaracingcar.domain;

import com.woowacourse.javaracingcar.util.RacingCarUtil;

public class Car {
    private final String name;
    private int position;

    public Car(String name) {
        this(name, 0);
    }

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
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
