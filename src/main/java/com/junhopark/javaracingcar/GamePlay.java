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

import java.util.Comparator;
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
        List<String> winnerNameList = findWinner();
        printWinner(winnerNameList);
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

    private List<String> findWinner() {
        if (carList.isEmpty()) {
            System.out.println("오류: 자동차 리스트가 초기화되지 않았습니다.");
            System.exit(0);
        }
        Car carWithMaxPosition = carList.stream()
                .max(Comparator.comparingInt(Car::getPosition))
                .get();
        return GameUtil.getNameListWithPosition(carWithMaxPosition.getPosition(), carList);
    }

    private void printWinner(List<String> winnerNameList) {
        StringBuilder sb = new StringBuilder();
        winnerNameList.stream()
                .map(s -> s + ", ")
                .forEach(sb::append);
        System.out.println(sb.substring(0, sb.length() - 2) + "가 최종 우승했습니다.");
    }
}
