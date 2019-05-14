package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {
    @Test
    void 정상적_사다리_높이_확인() {
        List<Line> lines = Arrays.asList(new Line(new NaturalNumber(3)),
                new Line(new NaturalNumber(3)),
                new Line(new NaturalNumber(3)));

        Ladder ladder = new Ladder(new NaturalNumber(3), new NaturalNumber(4));

        assertThat(ladder.getHeight()).isEqualTo(lines.size());
    }

}