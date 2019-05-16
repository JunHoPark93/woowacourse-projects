package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;

public class LadderGenerator {
    public static Ladder generateLadder(NaturalNumber height, NaturalNumber countOfPerson, BooleanGenerator generator) {
        Ladder ladder = new Ladder(height, countOfPerson);

        for (int h = 1; h <= height.getNumber(); h++) {
            for (int i = 1; i < countOfPerson.getNumber(); i++) {
                if (generator.generate()) {
                    ladder.putBridge(new NaturalNumber(h), new NaturalNumber(i));
                    i++;
                }
            }
        }

        return ladder;
    }
}
