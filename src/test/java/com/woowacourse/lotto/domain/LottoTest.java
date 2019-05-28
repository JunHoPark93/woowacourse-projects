package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoTest {
    @Test
    void 정상_로또_초기화() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6));

        assertThatCode(() -> new Lotto(lottoNumbers)).doesNotThrowAnyException();
    }

    @Test
    void 비정상_로또_중복숫자() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(5));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(()-> {
            new Lotto(lottoNumbers);
        }).withMessage("중복된 로또 숫자입니다");

    }

    @Test
    void 비정상_로또_6개가아닌_숫자() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(()-> {
            new Lotto(lottoNumbers);
        }).withMessage("로또는 6개의 숫자로 구성되어야 합니다");
    }
}
