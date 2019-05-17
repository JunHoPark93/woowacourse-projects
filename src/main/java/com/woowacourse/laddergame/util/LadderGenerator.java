package com.woowacourse.laddergame.util;

import com.woowacourse.laddergame.domain.Ladder;

public class LadderGenerator {
    public static Ladder generateLadder(NaturalNumber height, NaturalNumber countOfPerson, BooleanGenerator generator) {
        Ladder ladder = new Ladder(height, countOfPerson);

        // 처음 사다리 번호는 1번으로 칭한다
        for (int h = 1; h <= height.getNumber(); h++) {
            // 사람 수만 큼 (즉 Position 수 만큼) 반복한다
            loopInPerson(countOfPerson, generator, ladder, h);
        }

        return ladder;
    }

    private static void loopInPerson(NaturalNumber countOfPerson, BooleanGenerator generator, Ladder ladder, int height) {
        for (int i = 1; i < countOfPerson.getNumber(); i++) {
            i = attemptToPutBridge(generator, ladder, height, i);
        }
    }

    private static int attemptToPutBridge(BooleanGenerator generator, Ladder ladder, int height, int personIdx) {
        if (generator.generate()) {
            ladder.putBridge(new NaturalNumber(height), new NaturalNumber(personIdx));
            personIdx++;
        }
        return personIdx;
    }
}
