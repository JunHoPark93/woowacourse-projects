package com.woowacourse.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    Queue<Character> operationQueue;
    Queue<Integer> numberQueue;
    StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        operationQueue = new LinkedList<>();
        numberQueue = new LinkedList<>();
    }

    @Test
    void 정상입력_값반환() {
        // given
        operationQueue.add('+');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);

        // when
        stringCalculator = new StringCalculator(operationQueue, numberQueue);

        // then
        assertThat(stringCalculator.calculate()).isEqualTo(30);
    }

    @Test
    void 정상입력_이후_0으로_나누기_예외발생() {
        // given
        operationQueue.add('+');
        operationQueue.add('/');
        numberQueue.add(-2);
        numberQueue.add(3);
        numberQueue.add(0);

        // when
        stringCalculator = new StringCalculator(operationQueue, numberQueue);

        // then
        assertThrows(ArithmeticException.class, () -> stringCalculator.calculate());
    }

    @Test
    void 비정상입력_연산자_예외발생() {
        // given
        operationQueue.add('+');
        operationQueue.add('*');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator = new StringCalculator(operationQueue, numberQueue);
        });
    }

    @Test
    void 비정상입력_피연산자_예외발생() {
        // given
        operationQueue.add('+');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);
        numberQueue.add(5);

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator = new StringCalculator(operationQueue, numberQueue);
        });
    }

    @Test
    void 비정상입력_연산자_특수문자_예외발생() {
        // given
        operationQueue.add('?');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator = new StringCalculator(operationQueue, numberQueue);
            stringCalculator.calculate();
        });
    }
}
