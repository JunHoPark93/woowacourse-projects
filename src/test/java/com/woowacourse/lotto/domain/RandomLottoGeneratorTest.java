package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.RandomLottoGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoGeneratorTest {
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;

    @Test
    void 로또_범위_안의_랜덤_숫자_생성() {
        LottoGenerator lottoGenerator = new RandomLottoGenerator();

        for (int i = 0; i < 10000; i++) {
            assertThat(validLottoRange(lottoGenerator.generateNumber())).isTrue();
        }
    }

    private boolean validLottoRange(int generateNumber) {
        return MIN_LOTTO_NUM <= generateNumber && generateNumber <= MAX_LOTTO_NUM;
    }
}