package com.woowacourse.javaracingcar.testgenerator;

import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;

public class TestNumberGenerator implements NumberGenerator {
    private int[] numbers;
    private int index;

    public TestNumberGenerator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generateNumber() {
        return numbers[index++];
    }
}
