package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoTest {
    @Test
    void 의도된_로또_초기화() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThatCode(() -> new Lotto(lottoGenerator)).doesNotThrowAnyException();
    }

    @Test
    void 비정상_로또_중복숫자() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 5));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoGenerator))
                .withMessage("로또 생성 에러");
    }

    @Test
    void 비정상_로또_6개가아닌_숫자() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoGenerator))
                .withMessage("로또 생성 에러");
    }
}
