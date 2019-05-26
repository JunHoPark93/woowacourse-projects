package com.woowacourse.javacoordinate.domain;

public class Result {
    private final String figureName;
    private final String resultType;
    private final double result;

    public Result(final String figureName, final String resultType, final double result) {
        this.figureName = figureName;
        this.resultType = resultType;
        this.result = result;
    }

    public String getFigureName() {
        return figureName;
    }

    public String getResultType() {
        return resultType;
    }

    public double getResult() {
        return result;
    }
}
