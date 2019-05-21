package com.woowacourse.fuelinjection;

public abstract class Car {
    private int tripDistance;
    private int distancePerLiter;

    Car(int tripDistance, int distancePerLiter) {
        this.tripDistance = tripDistance;
        this.distancePerLiter = distancePerLiter;
    }

    /**
     * 차종의 이름
     */
    abstract String getName();

    /**
     * 주입해야할 연료량을 구한다.
     */
    double getChargeQuantity() {
        return (double) tripDistance / distancePerLiter;
    }
}
