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
import com.junhopark.javaracingcar.util.GameUtil;

import java.util.List;
import java.util.stream.IntStream;

public class GamePlay {
    private List<Car> carList;
    private int loop;

    public GamePlay(List<Car> carList, int loop) {
        this.carList = carList;
        this.loop = loop;
    }

    public void play() {
        playWithLoopNumber();
    }

    private void playWithLoopNumber() {
        for (int i = 0; i < loop; i++) {
            moveCarOrNot(carList);
            printCars(carList);
        }
    }

    private void moveCarOrNot(List<Car> carList) {
        IntStream.range(0, carList.size())
                .filter(i -> GameUtil.isMoveCondition())
                .forEach(i -> carList.get(i).moveForward());
    }

    private void printCars(List<Car> carList) {
        for (Car car : carList) {
            System.out.print(car.getName() + " : ");
            int position = car.getPosition();
            for (int i = 0; i < position; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }
}
