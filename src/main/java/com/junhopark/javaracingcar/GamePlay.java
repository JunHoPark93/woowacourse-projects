/*
 * Class:   GamePlay
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-30
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javaracingcar;

import com.junhopark.javaracingcar.domain.Car;

import java.util.List;

public class GamePlay {
    private List<Car> carList;
    private int loop;

    public GamePlay(List<Car> carList, int loop) {
        this.carList = carList;
        this.loop = loop;
    }
}
