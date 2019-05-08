package com.woowacourse.lecture;

import com.woowacourse.lecture.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void 덧셈() {
        assertThat(calculator.add(2,3)).isEqualTo(5);
    }

    @Test
    void 뺄셈() {
        assertThat(calculator.substract(5,1)).isEqualTo(4);
    }

    @AfterEach
    void tearDown() {
        System.out.println("after each called");
    }
}
