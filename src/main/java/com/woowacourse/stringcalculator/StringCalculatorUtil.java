package com.woowacourse.stringcalculator;

import java.util.LinkedList;
import java.util.Queue;

public class StringCalculatorUtil {

    public static StringCalculator parseCalculator(String input) {
        // 공백 검사
        input = removeSpaces(input);

        Queue<Character> operatorQueue = new LinkedList<>();
        Queue<Integer> numberQueue = new LinkedList<>();
        String[] tokens = input.split(" ");
        numberQueue.add(Integer.parseInt(tokens[0]));

        int tokenLength = tokens.length;
        for (int i = 1; i < tokenLength; i += 2) {
            checkIfValidTokens(tokens, tokenLength, i);

            operatorQueue.add(tokens[i].charAt(0));
            numberQueue.add(Integer.parseInt(tokens[i+1]));
        }

        return new StringCalculator(operatorQueue, numberQueue);
    }

    private static String removeSpaces(String input) {
        if (input.trim().length() == 0) {
            throw new IllegalArgumentException("비어있는 식을 입력할 수 없습니다");
        }
        return input.trim();
    }

    private static void checkIfValidTokens(String[] tokens, int length, int i) {
        if (!isOperator(tokens[i]) || i + 1 >= length || !isNumber(tokens[i+1])) {
            throw new IllegalArgumentException("올바르지 않은 식입니다.");
        }
    }

    private static boolean isNumber(String mayBeNumber) {
        try {
            Integer.parseInt(mayBeNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String maybeOperator) {
        if (maybeOperator.length() == 0) {
            return false;
        }

        char c = maybeOperator.charAt(0);
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
