package com.woowacourse.fuelinjection;

public class Avante extends Car {
    private static final int FUEL_EFFICIENCY = 15;
    private static final String CAR_NAME = "Avante";
    private int tripDistance;

    public Avante(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return FUEL_EFFICIENCY;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return CAR_NAME;
    }

    @Override
    double getChargeQuantity() {
        return super.getChargeQuantity();
    }
}
