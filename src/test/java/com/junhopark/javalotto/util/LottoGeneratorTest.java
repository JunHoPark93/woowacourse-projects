/*
 * Class:   LottoGeneratorTest
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-06
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javalotto.util;

import com.junhopark.javalotto.domain.Lotto;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_NUM = 6;

    @Test
    public void 로또번호가_범위에맞게_생성되야한다() {
        for (int i = 0; i < 1000; i++) {
            // given
            Lotto generatedLotto = LottoGenerator.generateLotto();

            // when
            boolean isValid = generatedLotto.getNumbers().stream().allMatch(this::isValidLottoRange);

            // then
            assertThat(isValid).isTrue();
        }
    }

    @Test
    public void 로또번호가_6개_생성되야한다() {
        // given
        Lotto generatedLotto = LottoGenerator.generateLotto();

        // when
        int lottoLength = generatedLotto.getNumbers().size();

        // then
        assertThat(lottoLength).isEqualTo(LOTTO_NUM);
    }

    @Test
    public void 로또번호가_중복생성_되면안된다() {
        for (int i = 0; i < 1000; i++) {
            // given
            Lotto generatedLotto = LottoGenerator.generateLotto();

            // when
            boolean isDuplicate = isContainDuplicateNumber(generatedLotto.getNumbers());

            // then
            assertThat(isDuplicate).isFalse();
        }
    }

    private boolean isValidLottoRange(Integer i) {
        return (MIN_LOTTO_NUM <= i) && (i <= MAX_LOTTO_NUM);
    }

    private boolean isContainDuplicateNumber(List<Integer> generatedLotto) {
        HashSet<Integer> set = new HashSet<>(generatedLotto);
        return set.size() != LOTTO_NUM;
    }
}