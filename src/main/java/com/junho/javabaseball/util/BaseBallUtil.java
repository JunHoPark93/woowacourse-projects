/*
 * Class:   BaseBallUtil
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-26
 *
 * Author:  Jun Ho Park
 *
 */

package com.junho.javabaseball.util;

import com.junho.javabaseball.model.BaseBallStatus;

import java.util.concurrent.ThreadLocalRandom;

public class BaseBallUtil {
    private static final int BASEBALL_NUM = 3;      // 야구 게임 3자리 수 기준
    private static final int NUMBER_RANGE = 10;     // 야구 게임 숫자 범위

    public static int[] generateThreeDigitNumbers() {
        boolean[] numberCheck = new boolean[NUMBER_RANGE];
        int[] numbers = new int[BASEBALL_NUM];
        int count = 0;

        while (count < BASEBALL_NUM) {
            // nextInt 함수는 0부터 시작하므로 인자로 1을 빼주고 결과값에 1을 더해야 1~9까지의 범위를 얻는다
            int number = ThreadLocalRandom.current().nextInt(NUMBER_RANGE - 1) + 1;
            if (!numberCheck[number]) {
                numbers[count] = number;
                numberCheck[number] = true;
                count++;
            }
        }
        return numbers;
    }

    public static String guessBaseBall(int[] generatedNumbers, int[] guessNumbers) {
        BaseBallStatus baseBallStatus = new BaseBallStatus(0,0);
        for (int i = 0; i < BASEBALL_NUM; i++) {
            countStrike(baseBallStatus, generatedNumbers[i], guessNumbers[i]);
            countBall(baseBallStatus, generatedNumbers[i], i, guessNumbers);
        }
        return baseBallStatus.toString();
    }

    private static void countStrike(BaseBallStatus baseBallStatus, int targetNumber, int guessNumber) {
        if (targetNumber == guessNumber) {
            baseBallStatus.setStrike(baseBallStatus.getStrike() + 1);
        }
    }

    private static void countBall(BaseBallStatus baseBallStatus, int targetNumber, int targetIndex, int[] guessNumbers) {
        for (int j = 0; j < BASEBALL_NUM; j++) {
            if (j != targetIndex && guessNumbers[j] == targetNumber) {
                baseBallStatus.setBall(baseBallStatus.getBall() + 1);
            }
        }
    }
}
