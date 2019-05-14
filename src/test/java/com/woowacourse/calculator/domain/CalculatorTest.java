package com.woowacourse.calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CalculatorTest {
    @Test
    void 빈_문자열입력() {
        String emptyString = "";

        Calculator calculator = new Calculator(emptyString);
        assertThat(calculator.calculate()).isEqualTo(0);
    }

    @Test
    void null_입력() {
        String nullString = null;

        Calculator calculator = new Calculator(nullString);
        assertThat(calculator.calculate()).isEqualTo(0);
    }

    @Test
    void 숫자_하나_입력() {
        String singleNumber = "1";

        Calculator calculator = new Calculator(singleNumber);
        assertThat(calculator.calculate()).isEqualTo(1);
    }

    @Test
    void 구분자로_계산() {
        String input = "1,2:3";

        Calculator calculator = new Calculator(input);
        assertThat(calculator.calculate()).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_계산() {
        String input = "//;\n1;2;3";

        Calculator calculator = new Calculator(input);
        assertThat(calculator.calculate()).isEqualTo(6);
    }

    @Test
    void 음수_입력_예외처리() {
        String input = "-1,2,3";

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            Calculator calculator = new Calculator(input);
        }).withMessage("음수는 입력할 수 없습니다");
    }
}
