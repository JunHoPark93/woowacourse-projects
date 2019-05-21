package com.woowacourse.fuelinjection;

public class K5 extends Car{
    private static final int FUEL_EFFICIENCY = 13;
    private static final String CAR_NAME = "K5";
    private int tripDistance;

    public K5(int tripDistance) {
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
