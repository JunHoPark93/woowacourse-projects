package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;
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
    void 실제_사다리() {
        BooleanGenerator generator = new RandomBooleanGenerator();
        LadderGenerator ladderGenerator = new LadderGenerator();
        Ladder ladder = ladderGenerator.generateLadder(new NaturalNumber(3), new NaturalNumber(4), generator);
        System.out.println(ladder.toString());
    }
}
