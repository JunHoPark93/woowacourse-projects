package com.woowacourse.laddergame.domain;

import java.util.function.Function;

public enum Position {
    LEFT((index) -> index - 1),
    RIGHT((index) -> index + 1),
    NONE((index) -> index);

    private static final int LADDER_INDEX_BOUNDARY = 0;

    Function<Integer, Integer> function;

    Position(Function<Integer, Integer> function) {
        this.function = function;
    }

    int move(int index, int maxIndex) {
        if (index <= LADDER_INDEX_BOUNDARY) {
            throw new IllegalArgumentException("인덱스는 1부터 시작해야 합니다");
        }
        if (index > maxIndex) {
            throw new IllegalArgumentException("최대 인덱스보다 큰 인덱스입니다");
        }
        return this.function.apply(index);
    }
}
