package com.woowacourse.laddergame.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LadderGeneratorTest {
    @Test
    void 사다리를_놓는다() {
        boolean[] testData = {true};

        BooleanGenerator generator = new TestBooleanGenerator(testData);

        assertThat(generator.generate()).isTrue();
    }

    @Test
    void 사다리를_놓지_않는다() {
        boolean[] testData = {false};

        BooleanGenerator generator = new TestBooleanGenerator(testData);

        assertThat(generator.generate()).isFalse();
    }
}
