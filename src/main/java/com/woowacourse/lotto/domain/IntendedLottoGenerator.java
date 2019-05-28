package com.woowacourse.lotto.domain;

import java.util.List;

public class IntendedLottoGenerator implements LottoGenerator {
    private List<Integer> numbers;
    private int index;

    public IntendedLottoGenerator(List<Integer> numbers) {
        this.numbers = numbers;
        this.index = 0;
    }

    @Override
    public int generateNumber() {
        try {
            return numbers.get(index++);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("로또 생성 에러");
        }
    }
}
