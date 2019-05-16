package com.woowacourse.laddergame.domain;

public class Result {
    private String result;

    public Result(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("%-6.6s", result);
    }
}
