package com.woowacourse.stringcalculator;

import java.util.Queue;

public class StringCalculator {
    private Queue<Character> operatorQueue;
    private Queue<Integer> numberQueue;

    public StringCalculator(Queue<Character> operatorQueue, Queue<Integer> numberQueue) {
        if (operatorQueue.size() != numberQueue.size() - 1) {
            throw new IllegalArgumentException("연산자와 피연산자 갯수가 맞지 않습니다");
        }

        this.operatorQueue = operatorQueue;
        this.numberQueue = numberQueue;
    }

    public int calculate() {
        int sum = numberQueue.poll();
        int length = numberQueue.size();
        for (int i = 0; i < length; i++) {
            int curNum = numberQueue.poll();
            char operator = operatorQueue.poll();
            sum = singleCalculate(curNum, operator, sum);
        }

        return sum;
    }

    private int singleCalculate(int curNum, char operator, int sum) {
        if (operator == '+') {
            return sum + curNum;
        }

        if (operator == '-') {
            return sum - curNum;
        }

        if (operator == '*') {
            return sum * curNum;
        }

        if (operator == '/') {
            if (curNum == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다");
            }

            return sum / curNum;
        }

        throw new IllegalArgumentException("Invalid operation");
    }

    public Queue<Character> getOperatorQueue() {
        return operatorQueue;
    }

    public Queue<Integer> getNumberQueue() {
        return numberQueue;
    }
}
