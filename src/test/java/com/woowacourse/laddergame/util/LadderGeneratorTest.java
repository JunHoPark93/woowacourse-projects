package com.woowacourse.laddergame.util;

import com.woowacourse.laddergame.domain.Ladder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LadderGeneratorTest {
    @Test
    void 사다리를_놓는다() {
        boolean[] testData = {true};

        BooleanGenerator generator = new TestBooleanGenerator(testData);

        assertThat(generator.generate()).isTrue();
    }

    @Test
    void 사다리를_놓지_않는다() {
        boolean[] testData = {false};

        BooleanGenerator generator = new TestBooleanGenerator(testData);

        assertThat(generator.generate()).isFalse();
    }

    @Test
    void 실제_사다리_확인용_메서드() {
        // 랜덤이 제대로 먹는지 콘솔에서 확인한다
        BooleanGenerator generator = new RandomBooleanGenerator();
        NaturalNumber height = new NaturalNumber(3);
        NaturalNumber countOfPerson = new NaturalNumber(4);
        Ladder ladder = LadderGenerator.generateLadder(height, countOfPerson, generator);

        System.out.println(ladder.toString());
    }
}
