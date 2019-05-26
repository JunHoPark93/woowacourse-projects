package com.woowacourse.fuelinjection;

public class Sonata extends Car {
    private static final int DISTANCE_PER_LITER = 10;
    private static final String CAR_NAME = "Sonata";

    public Sonata(int tripDistance) {
        super(tripDistance, DISTANCE_PER_LITER);
    }

    @Override
    public String getName() {
        return CAR_NAME;
    }
}
