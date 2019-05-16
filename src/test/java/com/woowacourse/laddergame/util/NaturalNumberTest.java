package com.woowacourse.laddergame.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class NaturalNumberTest {
    @Test
    void 자연수() {
        int number = 3;

        NaturalNumber naturalNumber = new NaturalNumber(number);

        assertThat(naturalNumber.getNumber()).isEqualTo(3);
    }

    @Test
    void 인덱스반환() {
        int number = 3;

        NaturalNumber naturalNumber = new NaturalNumber(number);

        assertThat(naturalNumber.convertIndex()).isEqualTo(2);
    }

    @Test
    void 음수() {
        int number = -10;

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            NaturalNumber naturalNumber = new NaturalNumber(number);
        }).withMessage("자연수가 아닙니다.");
    }
}