package com.woowacourse.calculator.domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    private static final Pattern PATTERN = Pattern.compile("//(.)\n(.*)");
    private static final String DEFAULT_NUMBER = "0";
    private static final String DEFAULT_DELIMETER = ":|,";
    private static final int NEGATIVE_BOUND = 0;

    private String expression;
    private String delimeter;
    private List<Integer> numbers;

    public Calculator(String input) {
        init(input);
        numbers = parseNumbers(expression);
    }

    void init(String input) {
        if (checkEmptyOrNull(input)) return;
        if (checkCustomDelimiter(input)) return;

        expression = input;
        delimeter = DEFAULT_DELIMETER;
    }

    private boolean checkCustomDelimiter(String input) {
        Matcher m = PATTERN.matcher(input);
        if (m.find()) {
            String customDelimiter = m.group(1);
            expression = m.group(2);
            delimeter = customDelimiter;
            return true;
        }
        return false;
    }

    private boolean checkEmptyOrNull(String input) {
        if (ifNullOrEmpty(input)) {
            expression = DEFAULT_NUMBER;
            delimeter = DEFAULT_DELIMETER;
            return true;
        }
        return false;
    }


    List<Integer> parseNumbers(String expression) {
        return Arrays.stream(expression.split(delimeter))
                .map(this::checkIfNegative)
                .collect(Collectors.toList());
    }

    int checkIfNegative(String number) {
        int no = Integer.parseInt(number);

        if (no < NEGATIVE_BOUND) {
            throw new RuntimeException("음수는 입력할 수 없습니다");
        }

        return no;
    }

    private boolean ifNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public int calculate() {
        if (numbers.size() == 0) {
            return 0;
        }

        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}