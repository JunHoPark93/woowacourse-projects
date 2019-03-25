package com.junho.javabaseball;

import com.junho.javabaseball.util.BaseBallUtil;

import java.util.Scanner;

/**
 * @author junho.park
 */
public class GameHelper {
    private static final int RESTART = 1;
    private static final int EXIT = 2;

    public static void play(Scanner sc, int[] generateNumbers) {
        while (true) {
            int[] guessNumbers = getUserInput(sc);

            String guessString = tryGuess(generateNumbers, guessNumbers);
            System.out.println(guessString);

            if (GameHelper.endCondition(guessString)) {
                break;
            }
        }
    }

    public static boolean endCondition(String guessString) {
        if (guessString.equals("3 스트라이크")) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
            return true;
        }
        return false;
    }

    public static int isContinue(Scanner sc) {
        while (true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String input = sc.nextLine();

            if (!isStringNumberFormat(input)) {
                continue;
            }

            int exitCode = Integer.parseInt(input);

            if (exitCode == 1) {
                return RESTART;
            } else if (exitCode == 2) {
                return EXIT;
            } else {
                System.out.println("1 또는 2를 입력하세요.");
            }
        }
    }

    private static int[] getUserInput(Scanner sc) {
        int[] guessNumbers = new int[3];
        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            String input = sc.nextLine();

            if (!isStringNumberFormat(input) || !isStringLengthThree(input) || !isNumberInRange(input)) {
                continue;
            }

            return parseStringToIntegerArray(input, guessNumbers);
        }
    }

    private static String tryGuess(int[] generateNumbers, int[] guessNumbers) {
        return BaseBallUtil.guessBaseBall(generateNumbers, guessNumbers);
    }

    private static int[] parseStringToIntegerArray(String input, int[] guessNumbers) {
        for (int i = 0; i < 3; i++) {
            guessNumbers[i] = Character.getNumericValue(input.charAt(i));
        }
        return guessNumbers;
    }

    private static boolean isNumberInRange(String input) {
        for (int i = 0; i < 3; i++) {
            if (input.charAt(i) == '0') {
                System.out.println("0은 입력할 수 없습니다.");
                return false;
            }
        }
        return true;
    }

    private static boolean isStringLengthThree(String input) {
        if (input.length() != 3) {
            System.out.println("길이가 3이 아닙니다.");
            return false;
        }
        return true;
    }

    private static boolean isStringNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식이 아닙니다.");
            return false;
        }
        return true;
    }
}
