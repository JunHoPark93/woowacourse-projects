/*
 * Class:   GameUtilTest
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-30
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javaracingcar.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameUtilTest {
    @Test
    public void shouldGetValidCarNames_정상입력() {
        // given
        String input = "pobi,crong,honux";

        // when
        boolean isValid = GameUtil.isCarNameStringValid(input);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldNotGetValidCarNames_길이가_5이상인_입력() {
        // given
        String input = "pobi,crong,honux,woowahanpobi";

        // when
        boolean isValid = GameUtil.isCarNameStringValid(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldNotGetValidCarNames_쉼표가_연속한_입력() {
        // given
        String input = "pobi,,crong,honux";

        // when
        boolean isValid = GameUtil.isCarNameStringValid(input);

        // then
        assertThat(isValid).isFalse();
    }
}