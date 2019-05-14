package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;

import java.util.ArrayList;
import java.util.List;

public class Ladder {
    private List<Line> lines;

    public Ladder(NaturalNumber height, NaturalNumber countOfPerson) {
        lines = new ArrayList<>();
        for (int h = 0; h < height.getNumber(); h++) {
            lines.add(new Line(countOfPerson));
        }
    }

    public int getHeight() {
        return lines.size();
    }
}
