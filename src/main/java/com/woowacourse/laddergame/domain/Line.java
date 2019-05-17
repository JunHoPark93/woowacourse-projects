package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Position> positions;

    public Line(NaturalNumber countOfPerson) {
        positions = new ArrayList<>();

        for (int i = 0; i < countOfPerson.getNumber(); i++) {
            positions.add(Position.NONE);
        }
    }

    public int getPositionCount() {
        return positions.size();
    }

    public void putBridge(NaturalNumber number) {
        if (number.getNumber() >= positions.size()) {
            throw new IllegalArgumentException("다리를 놓을 수 없습니다");
        }

        if (positions.get(number.convertIndex()) != Position.NONE) {
            throw new IllegalArgumentException("다리가 존재하거나 연속되게 놓을 수 없습니다.");
        }

        positions.set(number.convertIndex(), Position.RIGHT);
        positions.set(number.convertIndex() + 1, Position.LEFT);
    }

    public boolean isBridgeExist(int index) {
        return positions.get(index) != Position.NONE;
    }

    public int takeLine(NaturalNumber positionNo) {
        return positions.get(positionNo.convertIndex()).move(positionNo.getNumber());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Position position : positions) {
            if (position.equals(Position.LEFT)) {
                sb.append("-----|");
                continue;
            }
            sb.append("     |");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        return positions != null ? positions.equals(line.positions) : line.positions == null;
    }

    @Override
    public int hashCode() {
        return positions != null ? positions.hashCode() : 0;
    }
}
