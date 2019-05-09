package com.woowacourse.javaracingcar.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RacingCarUtilTest {
    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honux",
                            "pobi,jay,jason",
                            "pobi, jay, jason, tim",
                            "jay,lisa"})
    void 정상_이름_입력(String inputs) {
        assertThat(RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(inputs))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi   ,   crong, honux ",
                            "   pobi, crong,honux",
                            "pobi, crong,      honux"})
    void 정상_이름_입력_공백처리(String inputs) {
        assertThat(RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(inputs))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honuxxx",
            "pobiiiiiiiiii,crong,honux",
            "thelongestnameintheword"})
    void 비정상_이름_입력_5자초과(String inputs) {
        assertThrows(IllegalArgumentException.class,
                () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(inputs)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"p obi, crong,ho x",
                            " po bi, cro n",
                            "pobi,crong,hon x"})
    void 비정상_공백처리(String inputs) {
        assertThrows(IllegalArgumentException.class,
                () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(inputs)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,crong",
                            "pobi,pobi,Bobi"})
    void 비정상_중복된이름(String inputs) {
        assertThrows(IllegalArgumentException.class,
                () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(inputs)));
    }
}