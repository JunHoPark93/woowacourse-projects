/*
 * Class:   GameValidatorTest
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-31
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javaracingcar.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameValidatorTest {
    @Test
    public void shouldGetValidCarNames_정상입력() {
        // given
        String input = "pobi,crong,honux";

        // when
        boolean isValid = GameValidator.isCarNameInputValid(input);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldNotGetValidCarNames_길이가_5이상인_입력() {
        // given
        String input = "pobi,crong,honux,woowahanpobi";

        // when
        boolean isValid = GameValidator.isCarNameInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldNotGetValidCarNames_쉼표가_연속한_입력() {
        // given
        String input = "pobi,,crong,honux";

        // when
        boolean isValid = GameValidator.isCarNameInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldGetValidInputLoop_정상적_숫자_입력() {
        // given
        String input = "3";

        // when
        boolean isValid = GameValidator.isInteger(input);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldNotGetValidInputLoop_비정상_문자_입력() {
        // given
        String input = "dd";

        // when
        boolean isValid = GameValidator.isInteger(input);

        // then
        assertThat(isValid).isFalse();
    }
}