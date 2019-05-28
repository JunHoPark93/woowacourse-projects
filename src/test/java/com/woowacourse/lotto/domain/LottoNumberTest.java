package com.woowacourse.lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void 로또_번호_정상초기화(int lottoNum) {
        assertThatCode(() -> new LottoNumber(lottoNum)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -10})
    void 로또_번호_비정상_초기화(int lottoNum) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new LottoNumber(lottoNum);
        }).withMessage("로또 숫자 범위는 1~45 입니다");
    }
}
