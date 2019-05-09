package com.woowacourse.javaracingcar.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RacingCarUtilTest {
    @Test
    void 정상이름_입력() {
        // given
        String input = "pobi,crong,honux";

        // when then
        assertThat(RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(input))).isTrue();
    }

    @Test
    void 비정상_이름_입력_5자초과() {
        // given
        String input = "pobi,crong,honuxxx";

        // when then
        assertThrows(IllegalArgumentException.class, () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(input)));
    }

    @Test
    void 정상이름_입력_공백처리() {
        // given
        String input = "pobi   ,  crong,  honux   ";

        // when then
        assertThat(RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(input))).isTrue();
    }

    @Test
    void 공백처리_예외() {
        // given
        String input  = "p obi, crong, ho  x";

        // when then
        assertThrows(IllegalArgumentException.class, () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(input)));
    }

    @Test
    void 이름공백처리_예외() {
        // given
        String input  = "pobi,crong, ,honux";

        // when then
        assertThrows(IllegalArgumentException.class, () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(input)));
    }

    @Test
    void 중복된이름_예외() {
        // given
        String input = "pobi,crong,crong";

        // when then
        assertThrows(IllegalArgumentException.class, () -> RacingCarUtil.isValidNameInput(RacingCarUtil.splitIntoNames(input)));
    }
}