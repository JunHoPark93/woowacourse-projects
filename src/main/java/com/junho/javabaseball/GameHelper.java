/*
 * Class:   GameHelper
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-26
 *
 * Author:  Jun Ho Park
 *
 */

package com.junho.javabaseball;

import com.junho.javabaseball.util.BaseBallUtil;

import java.util.Scanner;

public class GameHelper {
    private static final int RESTART_CODE = 1;
    private static final int EXIT_CODE = 2;
    private static final int BASEBALL_NUM = 3;      // 야구 게임 3자리 수 기준

    public static boolean play(Scanner sc, int[] generateNumbers) {
        while (true) {
            int[] guessNumbers = getUserInput(sc);
            String guessString = tryGuess(generateNumbers, guessNumbers);

            System.out.println(guessString);

            if (endCondition(guessString)) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
                return true;
            }
        }
    }

    public static boolean endCondition(String guessString) {
        return guessString.equals("3 스트라이크");
    }

    public static int isContinue(Scanner sc) {
        while (true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

            String input = sc.nextLine();
            if (!isStringNumberFormat(input)) {
                continue;
            }

            int exitCode = Integer.parseInt(input);
            switch (exitCode) {
                case 1:
                    return RESTART_CODE;
                case 2:
                    return EXIT_CODE;
                default:
                    System.out.println("1 또는 2를 입력하세요.");
            }
        }
    }

    private static int[] getUserInput(Scanner sc) {
        while (true) {
            System.out.print("숫자를 입력해주세요 : ");

            String input = sc.nextLine();
            if (!isStringNumberFormat(input)) {
                System.out.println("숫자 형식이 아닙니다.");
                continue;
            }
            if (!isStringLengthEqualsToBaseBallNum(input)) {
                System.out.println("길이가 3이 아닙니다.");
                continue;
            }
            if(!isNumberInRange(input)) {
                System.out.println("0은 입력할 수 없습니다.");
                continue;
            }
            return parseStringToIntegerArray(input);
        }
    }

    private static String tryGuess(int[] generateNumbers, int[] guessNumbers) {
        return BaseBallUtil.guessBaseBall(generateNumbers, guessNumbers);
    }

    private static int[] parseStringToIntegerArray(String input) {
        int givenLength = input.length();
        int[] guessNumbers = new int[givenLength];
        for (int i = 0; i < givenLength; i++) {
            guessNumbers[i] = Character.getNumericValue(input.charAt(i));
        }
        return guessNumbers;
    }

    private static boolean isNumberInRange(String input) {
        for (int i = 0; i < BASEBALL_NUM; i++) {
            if (input.charAt(i) == '0') {
                return false;
            }
        }
        return true;
    }

    private static boolean isStringLengthEqualsToBaseBallNum(String input) {
        return input.length() == BASEBALL_NUM;
    }

    private static boolean isStringNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
