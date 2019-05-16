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

    @Test
    void 사다리에_다리_놓기() {
        Ladder ladder = new Ladder(new NaturalNumber(3), new NaturalNumber(4));

        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(1));
        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(3));
        ladder.putBridge(new NaturalNumber(2), new NaturalNumber(2));
        ladder.putBridge(new NaturalNumber(3), new NaturalNumber(3));

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
    void 사다리_비교() {
        Ladder ladder = new Ladder(new NaturalNumber(3), new NaturalNumber(4));

        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(1));
        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(3));
        ladder.putBridge(new NaturalNumber(2), new NaturalNumber(2));
        ladder.putBridge(new NaturalNumber(3), new NaturalNumber(3));

        Ladder ladder2 = new Ladder(new NaturalNumber(3), new NaturalNumber(4));

        ladder2.putBridge(new NaturalNumber(1), new NaturalNumber(1));
        ladder2.putBridge(new NaturalNumber(1), new NaturalNumber(3));
        ladder2.putBridge(new NaturalNumber(2), new NaturalNumber(2));
        ladder2.putBridge(new NaturalNumber(3), new NaturalNumber(3));

        assertThat(ladder.equals(ladder2)).isTrue();
    }

    @Test
    void 사다리_타기() {
        Ladder ladder = new Ladder(new NaturalNumber(3), new NaturalNumber(4));

        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(1));
        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(3));
        ladder.putBridge(new NaturalNumber(2), new NaturalNumber(2));
        ladder.putBridge(new NaturalNumber(3), new NaturalNumber(3));

        // 1번 사람은 4번 인덱스로 반환 되어야 함
        int firstIdx = ladder.takeLadder(new NaturalNumber(1));

        // 2번 사람은 1번 인덱스로 반환 되어야 함
        int secondIdx = ladder.takeLadder(new NaturalNumber(2));

        // 3번 사람은 3번 인덱스로 반환 되어야 함
        int thirdIdx = ladder.takeLadder(new NaturalNumber(3));

        // 4번 사람은 2번 인덱스로 반환 되어야 함
        int fourthIdx = ladder.takeLadder(new NaturalNumber(4));


        assertThat(firstIdx).isEqualTo(4);
        assertThat(secondIdx).isEqualTo(1);
        assertThat(thirdIdx).isEqualTo(3);
        assertThat(fourthIdx).isEqualTo(2);
    }
}