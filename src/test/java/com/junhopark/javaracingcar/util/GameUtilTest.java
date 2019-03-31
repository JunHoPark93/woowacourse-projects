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

import com.junhopark.javaracingcar.domain.Car;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void shouldGetValidInputLoop_정상적_숫자_입력() {
        // given
        String input = "3";

        // when
        boolean isValid = GameUtil.isLoopNumberValue(input);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldNotGetValidInputLoop_비정상_문자_입력() {
        // given
        String input = "dd";

        // when
        boolean isValid = GameUtil.isLoopNumberValue(input);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldGetValidCarList_자동차_리스트_반환() {
        // given
        String input = "pobi,crong,honux";
        List<Car> expected = new ArrayList<>();
        expected.add(new Car("pobi"));
        expected.add(new Car("crong"));
        expected.add(new Car("honux"));

        // when
        List<Car> actual = GameUtil.getCarList(input);

        // then
        assertThat(expected.retainAll(actual)).isTrue();
    }

    @Test
    public void shouldGetNameList_특정_위치_자동차이름_반환() {
        // given
        int position = 1;
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("pobi"));
        carList.add(new Car("crong"));
        carList.add(new Car("honux"));
        carList.get(0).moveForward();
        carList.get(2).moveForward();
        List<String> expected = new ArrayList<>();
        expected.add("pobi");
        expected.add("honux");

        // when
        List<String> actual = GameUtil.getNameListWithPosition(position, carList);

        // then
        assertThat(actual.equals(expected)).isTrue();
    }
}