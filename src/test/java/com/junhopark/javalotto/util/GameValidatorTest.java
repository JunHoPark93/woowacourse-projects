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

import com.junhopark.javalotto.domain.Lotto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameValidatorTest {
    @Test
    public void 구입금액_문자_입력을_처리한다() {
        // given
        String input = "우아";

        // when
        boolean isValid = GameValidator.isParsableToLong(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_로또한장가격_이하_입력을_처리한다() {
        // given
        long input = 300;

        // when
        boolean isValid = GameValidator.checkValidMoney(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_음수_입력을_처리한다() {
        // given
        long input = -100;

        // when
        boolean isValid = GameValidator.checkValidMoney(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_Long_범위를_벗어나는_큰수의_입력을_처리한다() {
        // given
        String input = "9223372036854775808";

        // when
        boolean isValid = GameValidator.isParsableToLong(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 구입금액_특수문자의_입력을_처리한다() {
        // given
        String input = "!@#$%^&*()";

        // when
        boolean isValid = GameValidator.isParsableToLong(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_6개_입력을_확인한다() {
        // given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        // when
        boolean isValid = GameValidator.checkValidNumberLotto(list);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 당첨번호입력_6개가_아닌_입력을_처리한다() {
        // given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // when
        boolean isValid = GameValidator.checkValidNumberLotto(list);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_문자입력을_처리한다() {
        // given
        List<String> list = new ArrayList<>();
        list.add("일");
        list.add("이");
        list.add("삼");
        list.add("사");
        list.add("오");
        list.add("육");

        // when
        boolean isValid = GameValidator.isListAllParsableToInt(list);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_범위를_벗어난_양수입력을_처리한다() {
        // given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(46);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // when
        boolean isValid = GameValidator.checkValidNumberLotto(list);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_범위를_벗어난_음수입력을_처리한다() {
        // given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(-6);

        // when
        boolean isValid = GameValidator.checkValidNumberLotto(list);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_단순개행을_처리한다() {
        // given
        String input = "\n";

        // when
        boolean isValid = GameValidator.isParsableToLong(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 당첨번호입력_중복된_입력을_처리한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,1,2,3,4,5);

        // when
        boolean isValid = GameValidator.checkUniqueNumberLotto(lottoNumber);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 보너스볼_입력_정상입력을_확인한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        Lotto lotto = new Lotto(lottoNumber);
        int bonusBall = 7;

        // when
        boolean isValid = GameValidator.checkValidBonusBall(lotto, bonusBall);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 보너스볼_입력_범위를_벗어난_입력을_처리한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        Lotto lotto = new Lotto(lottoNumber);
        int bonusBall = 100;

        // when
        boolean isValid = GameValidator.checkValidBonusBall(lotto, bonusBall);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 보너스볼_중복_입력을_처리한다() {
        // given
        List<Integer> lottoNumber = Arrays.asList(1,2,3,4,5,6);
        Lotto lotto = new Lotto(lottoNumber);
        int bonusBall = 6;

        // when
        boolean isValid = GameValidator.checkValidBonusBall(lotto, bonusBall);

        // then
        assertThat(isValid).isFalse();
    }
}