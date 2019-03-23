package com.junho.javabaseball.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author junho.park
 */
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
}