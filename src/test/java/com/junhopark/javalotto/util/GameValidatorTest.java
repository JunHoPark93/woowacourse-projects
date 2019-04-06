/*
 * Class:   GameValidatorTest
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-06
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javalotto.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameValidatorTest {
    @Test
    public void 구입금액_정상입력() {
        // given
        String input = "5000";

        // when
        boolean isValid = GameValidator.isPurchaseMoneyInputValid(input);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 구입금액_문자_입력을_처리한다() {
        // given
        String input = "우아";

        // when
        boolean isValid = GameValidator.isPurchaseMoneyInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_로또한장가격_이하_입력을_처리한다() {
        // given
        String input = "300";

        // when
        boolean isValid = GameValidator.isPurchaseMoneyInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_음수_입력을_처리한다() {
        // given
        String input = "-100";

        // when
        boolean isValid = GameValidator.isPurchaseMoneyInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_Long_범위를_벗어나는_큰수의_입력을_처리한다() {
        // given
        String input = "9223372036854775808";

        // when
        boolean isValid = GameValidator.isPurchaseMoneyInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_특수문자의_입력을_처리한다() {
        // given
        String input = "!@#$%^&*()";

        // when
        boolean isValid = GameValidator.isPurchaseMoneyInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }
}