package com.woowacourse.laddergame.domain;

public class TestBooleanGenerator implements BooleanGenerator {
    private boolean[] testData;
    private int index;

    public TestBooleanGenerator(boolean[] testData) {
        this.testData = testData;
        this.index = 0;
    }

    @Override
    public boolean generate() {
        return testData[index++];
    }
}
