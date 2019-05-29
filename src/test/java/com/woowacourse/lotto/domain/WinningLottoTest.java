package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class WinningLottoTest {
    @Test
    void 우승로또_초기화() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(lottoGenerator);
        LottoNumber bonusNumber = new LottoNumber(7);

        assertThatCode(() -> new WinningLotto(lotto, bonusNumber)).doesNotThrowAnyException();
    }

    @Test
    void 우승로또_비정상_중복된_보너스볼() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(lottoGenerator);
        LottoNumber bonusNumber = new LottoNumber(5);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .withMessage("중복된 보너스 볼");
    }
}