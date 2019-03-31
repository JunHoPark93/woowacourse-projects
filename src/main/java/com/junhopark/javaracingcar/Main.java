/*
 * Class:   Main
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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        List<Car> carList = getCarListFromUser(sc);
        System.out.println("시도할 회수는 몇회인가요?");
        int loop = getLoopFromUser(sc);
        sc.close();

        GamePlay gamePlay = new GamePlay(carList, loop);
        gamePlay.play();
    }

    private static List<Car> getCarListFromUser(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if (!GameUtil.isCarNameStringValid(input)) {
                System.out.println("유효하지 않은 입력입니다. 재입력 입력하세요.");
                continue;
            }
            return GameUtil.getCarList(input);
        }
    }

    private static int getLoopFromUser(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if (!GameUtil.isLoopNumberValue(input)) {
                System.out.println("숫자가 아닙니다. 재입력 하세요");
                continue;
            }
            return Integer.valueOf(input);
        }
    }
}
