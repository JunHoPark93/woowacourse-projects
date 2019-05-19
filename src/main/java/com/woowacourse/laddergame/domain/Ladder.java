package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.BooleanGenerator;
import com.woowacourse.laddergame.util.NaturalNumber;

import java.util.ArrayList;
import java.util.List;

public class Ladder {
    private final List<Line> lines;

    public Ladder(NaturalNumber height, NaturalNumber countOfPerson, BooleanGenerator booleanGenerator) {
        lines = new ArrayList<>();
        for (int h = 0; h < height.getNumber(); h++) {
            lines.add(new Line(countOfPerson));
        }
        initLadder(booleanGenerator, height, countOfPerson);
    }

    private void initLadder(BooleanGenerator booleanGenerator, NaturalNumber height, NaturalNumber countOfPerson) {
        for (int h = 1; h <= height.getNumber(); h++) {
            loopInPerson(booleanGenerator, h, countOfPerson);
        }
    }

    private void loopInPerson(BooleanGenerator booleanGenerator, int height, NaturalNumber countOfPerson) {
        for (int i = 1; i < countOfPerson.getNumber(); i++) {
            if (booleanGenerator.generate()) {
                putBridge(new NaturalNumber(height), new NaturalNumber(i));
                i++;
            }
        }
    }

    private void putBridge(NaturalNumber height, NaturalNumber position) {
        lines.get(height.convertIndex()).putBridge(position);
    }

    public int getHeight() {
        return lines.size();
    }

    public int takeLadder(NaturalNumber personNo) {
        int currentPosition = personNo.getNumber();
        for (int i = 0; i < getHeight(); i++) {
            currentPosition = lines.get(i).takeLine(new NaturalNumber(currentPosition));
        }
        return currentPosition;
    }

    public boolean isContainsLine(NaturalNumber height, Line line) {
        return lines.get(height.convertIndex()).equals(line);
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
