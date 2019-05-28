package com.woowacourse.lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PurchaseMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 14000})
    void 구입금액_정상_입력(int money) {
        assertThatCode(() -> new PurchaseMoney(money)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, 990})
    void 구입금액_비정상_입력(int money) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new PurchaseMoney(money);
        }).withMessage("구입 금액이 부족합니다");
    }
}
