package com.woowacourse.javaracingcar.util;

import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {
    @Test
    void 랜덤숫자생성_100회반복() {
        // given
        RandomNumberGenerator generator = new RandomNumberGenerator();

        // when then
        for (int i = 0; i < 100; i++) {
            assertThat(checkRandomNumberIsValidRange(generator.generateNumber())).isTrue();
        }
    }

    private boolean checkRandomNumberIsValidRange(int generateNumber) {
        return NumberGenerator.MIN <= generateNumber && generateNumber <= NumberGenerator.MAX;
    }
}