package com.woowacourse.javacoordinate.domain;

import com.woowacourse.javacoordinate.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateSystemTest {
    @Test
    void 좌표_평면_한_점_초기화() {
        Boolean[] line1 = new Boolean[25];

        for (int i = 0; i < line1.length; i++) {
            line1[i] = false;
        }

        line1[20] = true;

        CoordinateLine coordinateLine1 = new CoordinateLine(Arrays.asList(line1));
        CoordinateSystem coordinateSystem
                = new CoordinateSystem(Arrays.asList(coordinateLine1));

        assertThat(coordinateSystem.isMarked(20,0)).isTrue();
        assertThat(coordinateSystem.isMarked(19,0)).isFalse();
    }

    @Test
    void 라인테스트() {
        Boolean[] line = new Boolean[25];
        Boolean[] line2 = new Boolean[25];

        for (int i = 0; i < line.length; i++) {
            line[i] = false;
            line2[i] = false;
        }

        line2[2] = true;

        List<CoordinateLine> lines = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (i == 10) {
                lines.add(new CoordinateLine(Arrays.asList(line2)));
            }
            lines.add(new CoordinateLine(Arrays.asList(line)));
        }

        CoordinateSystem coordinateSystem
                = new CoordinateSystem(lines);

        OutputView.printCoordinateSystem(coordinateSystem);
    }
}
