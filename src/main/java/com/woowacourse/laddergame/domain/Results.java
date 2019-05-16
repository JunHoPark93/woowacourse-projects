package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;

import java.util.ArrayList;
import java.util.List;

public class Results {
    private List<Result> results;

    public Results() {
        this.results = new ArrayList<>();
    }

    public void add(Result result) {
        results.add(result);
    }

    public Result get(NaturalNumber resultNo) {
        return results.get(resultNo.convertIndex());
    }

    public boolean isContains(String result) {
        return results.contains(new Result(result));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append(result.toString());
        }
        return sb.toString();
    }
}
