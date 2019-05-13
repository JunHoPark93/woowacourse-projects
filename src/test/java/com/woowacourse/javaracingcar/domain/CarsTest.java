package com.woowacourse.javaracingcar.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CarsTest {
    @Test
    void 정상입력_자동차의이름() {
        // given
        List<String> names = Arrays.asList("jay", "lisa", "pobi");

        // when then
        assertThatCode(() -> {
            Cars cars = new Cars(names);
        }).doesNotThrowAnyException();
    }

    @Test
    void 비정상입력_자동차의이름_대소문자_중복() {
        // given
        List<String> names = Arrays.asList("jay", "lisa", "Lisa");

        // when then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Cars cars = new Cars(names);
        }).withMessage("자동차 이름들은 중복될 수 없습니다");
    }

    @Test
    void 비정상입력_자동차의이름_중복() {
        // given
        List<String> names = Arrays.asList("jay", "lisa", "jay");

        // when then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Cars cars = new Cars(names);
        }).withMessage("자동차 이름들은 중복될 수 없습니다");
    }
}