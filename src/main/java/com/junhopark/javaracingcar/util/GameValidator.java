/*
 * Class:   GameValidator
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-31
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javaracingcar.util;

import java.util.Arrays;

public class GameValidator {
    private static final int MAX_NAME_LENGTH = 5;

    public static boolean isCarNameInputValid(String input) {
        if (isContainsConsecutiveComma(input)) {
            return false;
        }
        String[] carNames = input.split(",");
        return Arrays.stream(carNames)
                .allMatch(GameValidator::isValidNameLength);
    }

    public static boolean isLoopNumberInputValid(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isContainsConsecutiveComma(String input) {
        int inputLength = input.length();
        for (int i = 0; i < inputLength - 1; i++) {
            if (input.charAt(i) == ',' && input.charAt(i + 1) == ',') {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidNameLength(String name) {
        return name.length() <= MAX_NAME_LENGTH;
    }
}
