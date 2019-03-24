package com.junho.javabaseball.util;

import com.junho.javabaseball.model.BaseBallStatus;

import java.util.Random;

/**
 * @author junho.park
 */
public class BaseBallUtil {
    private static final int BASEBALL_NUM = 3;

    public static int[] generateThreeDigitNumbers() {
        boolean[] numberCheck = new boolean[10];
        int[] numbers = new int[BASEBALL_NUM];
        Random random = new Random();
        int count = 0;

        while (count < BASEBALL_NUM) {
            int number = random.nextInt(9) + 1;

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
            checkStrike(baseBallStatus, generatedNumbers[i], guessNumbers[i]);
            checkBall(baseBallStatus, generatedNumbers[i], i, guessNumbers);
        }

        return baseBallStatus.toString();
    }

    private static void checkStrike(BaseBallStatus baseBallStatus, int targetNumber, int guessNumber) {
        if (targetNumber == guessNumber) {
            baseBallStatus.setStrike(baseBallStatus.getStrike() + 1);
        }
    }

    private static void checkBall(BaseBallStatus baseBallStatus, int targetNumber, int targetIndex, int[] guessNumbers) {
        for (int j = 0; j < BASEBALL_NUM; j++) {
            if (j != targetIndex && guessNumbers[j] == targetNumber) {
                baseBallStatus.setBall(baseBallStatus.getBall() + 1);
            }
        }
    }
}
