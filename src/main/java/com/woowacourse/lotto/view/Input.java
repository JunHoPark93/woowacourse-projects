package com.woowacourse.lotto.view;

import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputString() {
        return SCANNER.nextLine();
    }
}
