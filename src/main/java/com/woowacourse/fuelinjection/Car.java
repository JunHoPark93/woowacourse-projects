package com.woowacourse.fuelinjection;

public abstract class Car implements CarInterface {
    private int tripDistance;
    private int distancePerLiter;

    Car(int tripDistance, int distancePerLiter) {
        this.tripDistance = tripDistance;
        this.distancePerLiter = distancePerLiter;
    }

    /**
     * 주입해야할 연료량을 구한다.
     */
    @Override
    public double getChargeQuantity() {
        return (double) tripDistance / distancePerLiter;
    }
}
