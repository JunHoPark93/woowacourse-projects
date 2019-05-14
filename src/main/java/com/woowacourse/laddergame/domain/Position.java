package com.woowacourse.laddergame.domain;

import java.util.function.Function;

public enum Position {
    LEFT((index) -> index - 1),
    RIGHT((index) -> index + 1),
    NONE((index) -> index);

    Function<Integer, Integer> function;

    Position(Function<Integer, Integer> function) {
        this.function = function;
    }

    int move(int index) {
        return this.function.apply(index);
    }
}
