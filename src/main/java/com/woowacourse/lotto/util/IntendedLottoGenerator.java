package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class IntendedLottoGenerator implements LottoGenerator {
    private List<LottoNumber> numbers = new ArrayList<>();

    public IntendedLottoGenerator(List<Integer> numbers) {
        for (int number : numbers) {
            this.numbers.add(new LottoNumber(number));
        }
    }

    @Override
    public List<LottoNumber> generateNumber() {
        return numbers;
    }
}
