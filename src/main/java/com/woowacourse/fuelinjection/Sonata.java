package com.woowacourse.fuelinjection;

/**
 * @author junho.park
 */
public class Sonata extends Car {
    private static final int FUEL_EFFICIENCY = 10;
    private static final String CAR_NAME = "Sonata";
    private int tripDistance;

    public Sonata(int tripDistance) {
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
