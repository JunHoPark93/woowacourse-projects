/*
 * Class:   GameHelperTest
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-26
 *
 * Author:  Jun Ho Park
 *
 */

package com.junho.javabaseball;

import org.assertj.core.util.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameHelperTest {
    private static final List<String> ANSWER_LIST = Lists.newArrayList("1볼", "2볼", "낫싱", "1 스트라이크", "2 스트라이크", "1 스트라이크 1볼", "2스트라이크 1볼");
    private static final int RESTART = 1;
    private static final int EXIT = 2;
    private static Scanner restartInput;
    private static Scanner exitInput;
    private static Scanner rightInput;
    private static Scanner missInput;

    @BeforeClass
    public static void setUp() {
        restartInput = new Scanner("1");
        exitInput = new Scanner("2");
        rightInput = new Scanner("235");
        missInput = new Scanner("234");
    }

    @Test
    public void shouldGetEndMessage() {
        // given
        String str = "3 스트라이크";

        // when
        boolean isEnd = GameHelper.endCondition(str);

        // then
        assertThat(isEnd).isTrue();
    }

    @Test
    public void shouldNotGetEndMessage() {
        // given
        boolean isEnd;

        for (String s : ANSWER_LIST) {
            // when
            isEnd = GameHelper.endCondition(s);
            // then
            assertThat(isEnd).isFalse();
        }
    }

    @Test
    public void shouldGetGameRestart() {
        // given when
        int playingStatus = GameHelper.isContinue(restartInput);

        // then
        assertThat(playingStatus).isEqualTo(RESTART);
    }

    @Test
    public void shouldGetGameExit() {
        // given when
        int playingStatus = GameHelper.isContinue(exitInput);

        // then
        assertThat(playingStatus).isEqualTo(EXIT);
    }

    @Test
    public void gameSimulateSuccess() {
        // given
        int[] input = {2,3,5};

        // when
        boolean isEnd = GameHelper.play(rightInput, input);

        // then
        assertThat(isEnd).isTrue();
    }

    @Test
    public void gameSimulateFail() {
        // given
        int[] input = {2,3,5};

        // when then
        assertThatThrownBy(() -> GameHelper.play(missInput, input)).isInstanceOf(Exception.class);
    }

    @AfterClass
    public static void tearDown() {
        restartInput.close();
        exitInput.close();
        rightInput.close();
        missInput.close();
    }
}