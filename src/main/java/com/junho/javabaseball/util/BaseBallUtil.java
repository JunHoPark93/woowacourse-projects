package com.junho.javabaseball.util;

import java.util.Random;

/**
 * @author junho.park
 */
public class BaseBallUtil {
    public static int[] generateThreeDigitNumbers() {
        boolean[] numberCheck = new boolean[10];
        int[] numbers = new int[3];
        Random random = new Random();
        int count = 0;

        while (count < 3) {
            int number = random.nextInt(9) + 1;

            if (!numberCheck[number]) {
                numbers[count] = number;
                numberCheck[number] = true;
                count++;
            }
        }

        return numbers;
    }
}
