/*
 * Class:   Lotto
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-09
 *
 * Author:  WooWaBros
 *
 */

package com.junhopark.javalotto.domain;

import java.util.List;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
