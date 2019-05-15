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

    public boolean isContainsLine(NaturalNumber height, Line line) {
        return lines.get(height.convertIndex()).equals(line);
    }

    public void putBridge(NaturalNumber height, NaturalNumber position) {
        lines.get(height.convertIndex()).putBridge(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ladder ladder = (Ladder) o;

        return lines != null ? lines.equals(ladder.lines) : ladder.lines == null;
    }

    @Override
    public int hashCode() {
        return lines != null ? lines.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Line line : lines) {
            sb.append(line.toString()).append("\n");
        }
        return sb.toString();
    }
}
