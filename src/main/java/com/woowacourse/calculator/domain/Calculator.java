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

    private String expression;
    private String delimeter;
    private List<Integer> numbers;
    private int sum;

    public Calculator(String input) {
        init(input);
        numbers = parseNumbers(expression);
    }

    void init(String input) {
        if (ifNullOrEmpty(input)) {
            expression = DEFAULT_NUMBER;
            delimeter = DEFAULT_DELIMETER;
            return;
        }

        Matcher m = PATTERN.matcher(input);
        if (m.find()) {
            String customDelimiter = m.group(1);
            expression = m.group(2);
            delimeter = customDelimiter;
            return;
        }

        expression = input;
        delimeter = DEFAULT_DELIMETER;
    }


    List<Integer> parseNumbers(String expression) {
        return Arrays.stream(expression.split(delimeter))
                .map(this::parseNumber)
                .collect(Collectors.toList());
    }

    int parseNumber(String number) {
        int no = Integer.parseInt(number);

        if (no < 0) {
            throw new RuntimeException("음수는 입력할 수 없습니다");
        }

        return no;
    }

    private boolean ifNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public int calculate() {
        if (numbers.size() == 0) {
            return sum;
        }

        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }
}