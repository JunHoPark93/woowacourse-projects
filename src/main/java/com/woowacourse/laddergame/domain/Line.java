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


    public void putBridge(NaturalNumber index) {
        if (index.convertIndex() + 1 >= positions.size()) {
            throw new IllegalArgumentException("다리를 놓을 수 없습니다");
        }

        if (positions.get(index.convertIndex()) != Position.NONE) {
            throw new IllegalArgumentException("다리가 존재하거나 연속되게 놓을 수 없습니다.");
        }

        positions.set(index.convertIndex(), Position.RIGHT);
        positions.set(index.convertIndex() + 1, Position.LEFT);
    }

    public boolean isBridgeExist(int index) {
        return positions.get(index) != Position.NONE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).equals(Position.LEFT)) {
                sb.append("-----|");
                continue;
            }
            sb.append("     |");

        }
        return sb.toString();
    }


}
