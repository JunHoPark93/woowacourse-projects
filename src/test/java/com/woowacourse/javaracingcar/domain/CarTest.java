package com.woowacourse.javaracingcar.domain;

import com.woowacourse.javaracingcar.util.RacingCarUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "jay", "lisa"})
    void 정상_이름_입력(String name) {
        assertThatCode(() -> {
            Car car = new Car(name);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobiman", "jayman", "lisawoman"})
    void 비정상_이름_입력_5자초과(String name) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Car car = new Car(name);
        }).withMessage("이름은 5글자를 넘을 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"po b", "j y", "l s"})
    void 비정상_이름_입력_중간공백(String name) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Car car = new Car(name);
        }).withMessage("이름은 공백을 포함할 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -256})
    void 비정상_시도횟수_입력_(int tries) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                RacingCarUtil.checkValidTriesInput(tries))
                .withMessage("1이상 입력이 필요합니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"woowa", "can i type string value to tries?"})
    void 비정상_시도횟수_입력_문자열(String tries) {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() ->
                RacingCarUtil.convertTriesStringToInteger(tries));
    }
}