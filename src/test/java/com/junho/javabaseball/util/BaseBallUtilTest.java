/*
 * Class:   BaseBallUtilTest
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-26
 *
 * Author:  Jun Ho Park
 *
 */

package com.junho.javabaseball.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseBallUtilTest {

    @Test
    public void shouldGetThreeDigitNumber() {
        // given when
        int[] numbers = BaseBallUtil.generateThreeDigitNumbers();

        // then
        assertThat(numbers.length).isEqualTo(3);
    }

    @Test
    public void shouldGetThreeDigitDifferentNumber() {
        // given when
        int[] numbers = BaseBallUtil.generateThreeDigitNumbers();

        // then
        assertThat(areAllDifferentNumber(numbers)).isTrue();
    }

    private boolean areAllDifferentNumber(int[] numbers) {
        Set<Integer> numberSet = new HashSet<>();

        for (int i : numbers) {
            if (numberSet.contains(i)) {
                return false;
            }
            numberSet.add(i);
        }

        return true;
    }

    @Test
    public void shouldGetThreeStrike() {
        // given
        int[] generatedNumbers = {2, 6, 7};
        int[] guessNumbers = {2, 6, 7};

        // when
        String maybeThreeStrike = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeThreeStrike).isEqualTo("3 스트라이크");
    }

    @Test
    public void shouldGetNothing() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {4, 5, 6};

        // when
        String maybeNothing = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeNothing).isEqualTo("낫싱");
    }

    @Test
    public void shouldGetOneStrike() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {1, 7, 8};

        // when
        String maybeOneStrike = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeOneStrike).isEqualTo("1 스트라이크");
    }

    @Test
    public void shouldGetTwoStrike() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {1, 2, 8};

        // when
        String maybeTwoStrike = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeTwoStrike).isEqualTo("2 스트라이크");
    }

    @Test
    public void shouldGetOneStrikeTwoBall() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {1, 3, 2};

        // when
        String maybeOneStrikeTwoBall = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeOneStrikeTwoBall).isEqualTo("1 스트라이크 2볼");
    }

    @Test
    public void shouldGetTwoStrikeOneBall() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {3, 2, 3};

        // when
        String maybeTwoStrikeOneBall = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeTwoStrikeOneBall).isEqualTo("2 스트라이크 1볼");
    }

    @Test
    public void shouldGetOneBall() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {3, 7, 8};

        // when
        String maybeOneBall = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeOneBall).isEqualTo("1볼");
    }

    @Test
    public void shouldGetTwoBall() {
        // given
        int[] generatedNumbers = {1, 2, 3};
        int[] guessNumbers = {3, 1, 8};

        // when
        String maybeTwoBall = BaseBallUtil.guessBaseBall(generatedNumbers, guessNumbers);

        // then
        assertThat(maybeTwoBall).isEqualTo("2볼");
    }
}