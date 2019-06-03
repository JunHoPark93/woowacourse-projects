package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.LottoNumber;

import java.util.List;

public class IntendedLottoGenerator implements LottoGenerator {
    private List<Integer> numbers;
    private int index;

    public IntendedLottoGenerator(List<Integer> numbers) {
        this.numbers = numbers;
        this.index = 0;
    }

    @Override
    public LottoNumber generateNumber() {
        try {
            return new LottoNumber(numbers.get(index++));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("로또 생성 에러");
        }
    }
}
