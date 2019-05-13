package com.woowacourse.javaracingcar.util;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RacingCarUtilTest {
    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 200})
    void 정상_시도횟수_입력(int tries) {
        assertThatCode(() -> RacingCarUtil.checkValidTriesInput(tries))
                .doesNotThrowAnyException();
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -256})
    void 비정상_시도횟수_입력(int tries) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                RacingCarUtil.checkValidTriesInput(tries))
                .withMessage("1이상 입력이 필요합니다");
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {"woowa", "can i type string value to tries?"})
    void 비정상_시도횟수_입력_문자열(String inputs) {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() ->
                RacingCarUtil.convertTriesStringToInteger(inputs));
    }
}