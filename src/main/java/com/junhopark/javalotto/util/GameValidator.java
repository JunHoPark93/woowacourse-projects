/*
 * Class:   GameValidator
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-06
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javalotto.util;

import java.util.Arrays;
import java.util.List;

public class GameValidator {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_NUM = 6;

    public static boolean isPurchaseMoneyInputValid(String input) {
        if (!isParsableToLong(input)) {
            return false;
        }
        Long money = Long.parseLong(input);
        return isPositivePrice(money) && isOverMinimumLottoPrice(money);
    }

    public static boolean isWinningNumberInputValid(String input) {
        List<String> numberList = Arrays.asList(input.split(","));

        return isListAllParsableToInt(numberList)
                && isValidLottoSize(numberList)
                && isListAllValidLottoNumberRange(numberList);
    }

    public static boolean isBonusBallInputValid(List<Integer> lottoNumbers, String input) {
        if (!isParsableToInt(input)) {
            return false;
        }

        int bonusBall = Integer.parseInt(input);
        if(!isValidLottoNumberRange(bonusBall)) {
            return false;
        }
        return isLottoNumbersContainsBonusBall(lottoNumbers, bonusBall);
    }

    private static boolean isPositivePrice(Long money) {
        if (money < 0) {
            System.out.println("돈은 음수가 될 수 없습니다.");
            return false;
        }
        return true;
    }

    private static boolean isOverMinimumLottoPrice(Long money) {
        if (money < LOTTO_PRICE) {
            System.out.println("구매할 돈이 부족이 부족합니다.");
            return false;
        }
        return true;
    }

    private static boolean isValidLottoSize(List<String> numberList) {
        if (numberList.size() != LOTTO_NUM) {
            System.out.println("로또는 6개의 숫자가 필요합니다.");
            return false;
        }
        return true;
    }

    private static boolean isParsableToLong(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("64비트 정수입력 범위를 벗어났습니다.");
            return false;
        }
        return true;
    }

    private static boolean isListAllParsableToInt(List<String> numberList) {
        return numberList.stream().allMatch(GameValidator::isParsableToInt);
    }

    private static boolean isParsableToInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("32비트 정수입력 범위를 벗어났습니다.");
            return false;
        }
        return true;
    }

    private static boolean isListAllValidLottoNumberRange(List<String> numberList) {
        return numberList.stream()
                .map(Integer::valueOf)
                .allMatch(GameValidator::isValidLottoNumberRange);
    }

    private static boolean isValidLottoNumberRange(int number) {
        if ((number < MIN_LOTTO_NUM) || (MAX_LOTTO_NUM < number)) {
            System.out.println("로또 숫자의 범위를 벗어났습니다.");
            return false;
        }
        return true;
    }

    private static boolean isLottoNumbersContainsBonusBall(List<Integer> lottoNumbers, int bonusBall) {
        boolean isContain = lottoNumbers.stream().anyMatch(number -> number == bonusBall);
        if (isContain) {
            System.out.println("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
            return false;
        }
        return true;
    }
}
