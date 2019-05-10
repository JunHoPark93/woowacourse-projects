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
    @ValueSource(strings = {"pobi,crong,honux",
                            "pobi,jay,jason",
                            "pobi, jay, jason, tim",
                            "jay,lisa"})
    void 정상_이름_입력(String inputs) {
        // 이름 입력이 잘 되었다면 검증시 예외가 발생하지 않는다.
        assertThatCode(() -> RacingCarUtil.checkValidNameInput(RacingCarUtil.splitIntoNames(inputs)))
                .doesNotThrowAnyException();
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {"pobi   ,   crong, honux ",
                            "   pobi, crong,honux",
                            "pobi, crong,      honux"})
    void 정상_이름_입력_공백처리(String inputs) {
        assertThatCode(() -> RacingCarUtil.checkValidNameInput(RacingCarUtil.splitIntoNames(inputs)))
                .doesNotThrowAnyException();
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honuxxx",
                            "pobiiiiiiiiii,crong,honux",
                            "thelongestnameintheword"})
    void 비정상_이름_입력_5자초과처리(String inputs) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                RacingCarUtil.checkValidNameInput(RacingCarUtil.splitIntoNames(inputs)))
                .withMessage("이름은 5자를 넘을 수 없습니다");
    }

    @Order(4)
    @ParameterizedTest
    @ValueSource(strings = {"p obi, crong,ho x",
                            " po bi, cro n",
                            "pobi,crong,hon x"})
    void 비정상_이름_입력_공백처리(String inputs) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                RacingCarUtil.checkValidNameInput(RacingCarUtil.splitIntoNames(inputs)))
                .withMessage("이름에 공백을 포함할 수 없습니다");
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,crong",
                            "Jay, jay, Pobi",
                            "pobi,pobi,Bobi"})
    void 비정상_이름_입력_중복처리(String inputs) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                RacingCarUtil.checkValidNameInput(RacingCarUtil.splitIntoNames(inputs)))
                .withMessage("중복된 이름을 입력할 수 없습니다");
    }

    @Order(6)
    @ParameterizedTest
    @ValueSource(ints = {-1,-100,-256})
    void 비정상_시도횟수_입력(int tries) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                RacingCarUtil.checkValidTriesInput(tries))
                .withMessage("1이상 입력이 필요합니다");
    }

    @Order(7)
    @ParameterizedTest
    @ValueSource(strings = {"woowa", "can i type string value to tries?"})
    void 비정상_시도횟수_입력_문자열(String inputs) {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() ->
                RacingCarUtil.convertTriesStringToInteger(inputs));
    }
}