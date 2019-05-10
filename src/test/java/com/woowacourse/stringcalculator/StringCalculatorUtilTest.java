package com.woowacourse.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class StringCalculatorUtilTest {
    Queue<Character> expectedOperatorQueue;
    Queue<Integer> expectedNumberQueue;

    @BeforeEach
    void setUp() {
        expectedOperatorQueue = new LinkedList<>();
        expectedNumberQueue = new LinkedList<>();
    }

    @ParameterizedTest
    @ValueSource(strings = {"  4 + 2 * 5", "4 + 2 * 5"})
    void 정상문자열_입력 (String expression) {
        // given
        expectedOperatorQueue.add('+');
        expectedOperatorQueue.add('*');
        expectedNumberQueue.add(4);
        expectedNumberQueue.add(2);
        expectedNumberQueue.add(5);

        // when
        Queue<Character> actualOperatorQueue = StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
        Queue<Integer> actualNumberQueue = StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        int length = expectedNumberQueue.size() - 1;

        // then
        assertThat(expectedNumberQueue.poll()).isEqualTo(actualNumberQueue.poll());
        for (int i = 0; i < length; i++) {
            assertThat(expectedNumberQueue.poll()).isEqualTo(actualNumberQueue.poll());
            assertThat(expectedOperatorQueue.poll()).isEqualTo(actualOperatorQueue.poll());
        }
    }

    @Test
    void 비정상문자열_피연산자_갯수_불일치_예외발생 () {
        // given
        String expression = "4 + 2 -";

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });

    }

    @Test
    void 비정상문자열_중간에_공백이_2개이상인_문자열() {
        // given
        String expression = "4  + 2 * 5";

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });
    }

    @Test
    void 비정상입력_불규칙_문자열_예외발생() {
        // given
        String expression = "dfdfsdfda";

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });
    }

    @Test
    void 비정상입력_단순공백입력_예외발생() {
        // given
        String expression = "   ";

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });
    }
}
