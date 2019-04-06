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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameValidatorTest {
    @Test
    public void 구입금액_정상입력을_확인한다() {
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

    @Test
    public void 당첨번호입력_6개_입력을_확인한다() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        boolean isValid = GameValidator.isWinningNumberInputValid(input);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 당첨번호입력_6개가_아닌_입력을_처리한다() {
        // given
        String input = "1,2,3,4,5";

        // when
        boolean isValid = GameValidator.isWinningNumberInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_문자입력을_처리한다() {
        // given
        String input = "일,이,삼,사,오,육";

        // when
        boolean isValid = GameValidator.isWinningNumberInputValid(input);

        // then
        assertThat(isValid).isFalse();

    }

    @Test
    public void 당첨번호입력_범위를_벗어난_양수입력을_처리한다() {
        // given
        String input = "1,46,2,3,4,5";

        // when
        boolean isValid = GameValidator.isWinningNumberInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_범위를_벗어난_음수입력을_처리한다() {
        // given
        String input = "1,30,2,3,25,-10";

        // when
        boolean isValid = GameValidator.isWinningNumberInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_단순개행을_처리한다() {
        // given
        String input = "\n";

        // when
        boolean isValid = GameValidator.isWinningNumberInputValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 보너스볼_입력_정상입력을_확인한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        String bonusBall = "7";

        // when
        boolean isValid = GameValidator.isBonusBallInputValid(lottoNumber, bonusBall);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 보너스볼_입력_범위를_벗어난_입력을_처리한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        String bonusBall = "100";

        // when
        boolean isValid = GameValidator.isBonusBallInputValid(lottoNumber, bonusBall);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 보너스볼_중복_입력을_처리한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        String bonusBall = "6";

        // when
        boolean isValid = GameValidator.isBonusBallInputValid(lottoNumber, bonusBall);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 보너스볼_중복_단순개행을_처리한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        String bonusBall = "\n";

        // when
        boolean isValid = GameValidator.isBonusBallInputValid(lottoNumber, bonusBall);

        // then
        assertThat(isValid).isFalse();
    }
}