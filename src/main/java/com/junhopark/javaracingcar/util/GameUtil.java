/*
 * Class:   GameUtil
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-30
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javaracingcar.util;

public class GameUtil {
    private static final int MAX_NAME_LENGTH = 5;

    public static boolean isCarNameStringValid(String input) {
        if (isContainsConsecutiveComma(input)) {
            return false;
        }
        String[] carNames = input.split(",");
        for (String carName : carNames) {
            if (!checkIfValidLength(carName)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isContainsConsecutiveComma(String input) {
        int inputLength = input.length();
        for (int i = 0; i < inputLength-1; i++) {
            if (input.charAt(i) == ',' && input.charAt(i+1) == ',') {
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfValidLength(String s) {
        return s.length() <= MAX_NAME_LENGTH;
    }
}
