package com.woowacourse.fuelinjection;

public class K5 extends Car {
    private static final int DISTANCE_PER_LITER = 13;
    private static final String CAR_NAME = "K5";

    public K5(int tripDistance) {
        super(tripDistance, DISTANCE_PER_LITER);
    }

    @Override
    public String getName() {
        return CAR_NAME;
    }

    @Override
    public double getChargeQuantity() {
        return super.getChargeQuantity();
    }
}
