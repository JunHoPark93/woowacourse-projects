package com.woowacourse.javacoordinate.domain;

public class Result {
    private final double result;
    private final String resultType;

    public Result(double result, String resultType) {
        this.result = result;
        this.resultType = resultType;
    }

    public double getResult() {
        return result;
    }

    public String getResultType() {
        return resultType;
    }
}
