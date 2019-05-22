package com.woowacourse.javacoordinate.view;

import com.woowacourse.javacoordinate.domain.CoordinateLine;
import com.woowacourse.javacoordinate.domain.CoordinateSystem;

import java.util.List;

public class OutputView {
    public static void printCoordinateSystem(CoordinateSystem coordinateSystem) {
        List<CoordinateLine> lines = coordinateSystem.getCoordinateLines();

        for (int i = 24; i >= 0; i--) {
            if (i != 0 && i % 2 == 0) {
                System.out.println(String.format("%2d|", i) + lines.get(i));

                continue;
            }
            if (i == 0) {
                System.out.print(" 0+");
                for (int j = 0; j < 24; j++) {
                    System.out.print("--");
                }
                System.out.println();
                continue;
            }
            System.out.println("  " + "|" + lines.get(i));
        }

        // 마지막 줄
        for (int i = 0; i <= 24; i++) {
            if (i == 0) {
                System.out.print("   0");
                continue;
            }
            if (i % 2 == 0) {
                System.out.print(String.format("%4d", i));
            }
        }
    }
}
