package com.woowacourse.javacoordinate.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateSystemTest {
    @Test
    void 좌표_평면_한_점_초기화() {
        Boolean[] line1 = new Boolean[25];

        for (int i = 0; i < line1.length; i++) {
            line1[i] = false;
        }

        line1[20] = true;

        CoordinateLine coordinateLine = new CoordinateLine(Arrays.asList(line1));
        CoordinateSystem coordinateSystem
                = new CoordinateSystem(Arrays.asList(coordinateLine));

        assertThat(coordinateSystem.isMarked(20,0)).isTrue();
        assertThat(coordinateSystem.isMarked(19,0)).isFalse();
    }
}
