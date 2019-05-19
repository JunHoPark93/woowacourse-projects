package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.BooleanGenerator;
import com.woowacourse.laddergame.util.NaturalNumber;
import com.woowacourse.laddergame.util.TestBooleanGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {
    private NaturalNumber height;
    private NaturalNumber countOfPerson;
    private Ladder ladder;
    private BooleanGenerator booleanGenerator;
    private boolean[] testBooleanData;

    @BeforeEach
    void setUp() {
        height = new NaturalNumber(3);
        countOfPerson = new NaturalNumber(4);
        testBooleanData = new boolean[]{true, true, false, true, false, false, true};
        booleanGenerator = new TestBooleanGenerator(testBooleanData);
        ladder = new Ladder(height, countOfPerson, booleanGenerator);
    }

    @Test
    void 정상적_사다리_높이_확인() {
        List<Line> lines = Arrays.asList(new Line(new NaturalNumber(3)),
                new Line(new NaturalNumber(3)),
                new Line(new NaturalNumber(3)));

        assertThat(ladder.getHeight()).isEqualTo(lines.size());
    }

    @Test
    void 사다리에_다리_놓기() {
        // 첫 번째 줄
        Line line1 = new Line(new NaturalNumber(4));
        line1.putBridge(new NaturalNumber(1));
        line1.putBridge(new NaturalNumber(3));

        // 두 번째 줄
        Line line2 = new Line(new NaturalNumber(4));
        line2.putBridge(new NaturalNumber(2));

        // 세 번째 줄
        Line line3 = new Line(new NaturalNumber(4));
        line3.putBridge(new NaturalNumber(3));

        assertThat(ladder.isContainsLine(new NaturalNumber(1), line1)).isTrue();
        assertThat(ladder.isContainsLine(new NaturalNumber(2), line2)).isTrue();
        assertThat(ladder.isContainsLine(new NaturalNumber(3), line3)).isTrue();
    }

    @Test
    void 사다리타기_1번사람() {
        // 1번 사람은 4번 인덱스로 반환 되어야 함
        int firstIdx = ladder.takeLadder(new NaturalNumber(1));

        assertThat(firstIdx).isEqualTo(4);
    }

    @Test
    void 사다리타기_2번사람() {
        // 2번 사람은 1번 인덱스로 반환 되어야 함
        int secondIdx = ladder.takeLadder(new NaturalNumber(2));

        assertThat(secondIdx).isEqualTo(1);
    }

    @Test
    void 사다리타기_3번사람() {
        // 3번 사람은 3번 인덱스로 반환 되어야 함
        int thirdIdx = ladder.takeLadder(new NaturalNumber(3));

        assertThat(thirdIdx).isEqualTo(3);
    }

    @Test
    void 사다리타기_4번사람() {
        // 4번 사람은 2번 인덱스로 반환 되어야 함
        int fourthIdx = ladder.takeLadder(new NaturalNumber(4));

        assertThat(fourthIdx).isEqualTo(2);
    }
}