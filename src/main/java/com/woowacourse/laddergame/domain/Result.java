package com.woowacourse.laddergame.domain;

public class Result {
    private final String result;

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("%-6.6s", result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result1 = (Result) o;

        return result != null ? result.equals(result1.result) : result1.result == null;
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }
}
