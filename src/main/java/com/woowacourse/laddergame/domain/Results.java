package com.woowacourse.laddergame.domain;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append(result.toString());
        }
        return sb.toString();
    }
}
