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

import com.junhopark.javalotto.domain.Lotto;

import java.util.HashSet;
import java.util.List;

public class GameValidator {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_NUM = 6;

    public static boolean checkValidMoney(long longValue) {
        if (!GameValidator.isPositivePrice(longValue)) {
            System.out.println("돈은 음수가 될 수 없습니다.");
            return false;
        }
        if (!GameValidator.isOverMinimumLottoPrice(longValue)) {
            System.out.println("구매할 돈이 부족이 부족합니다.");
            return false;
        }
        return true;
    }

    public static boolean checkValidNumberLotto(List<Integer> list) {
        if (!GameValidator.isListValidLottoSize(list)) {
            System.out.println("로또는 6개의 숫자가 필요합니다.");
            return false;
        }
        if (!GameValidator.isListAllValidLottoNumberRange(list)) {
            System.out.println("로또 숫자 범위는 1~45 입니다.");
            return false;
        }
        return true;
    }

    public static boolean checkUniqueNumberLotto(List<Integer> list) {
        if (GameValidator.isListDuplicateEachOther(list)) {
            System.out.println("로또 숫자는 서로 중복될 수 없습니다.");
            return false;
        }
        return true;
    }

    public static boolean checkValidBonusBall(Lotto lotto, int bonusBall) {
        if (!GameValidator.isValidLottoNumberRange(bonusBall)) {
            System.out.println("로또 숫자 범위가 아닙니다.");
            return false;
        }
        if (GameValidator.isLottoNumbersContainsBonusBall(lotto, bonusBall)) {
            System.out.println("보너스볼은 당첨 로또 번호 6개 숫자와 중복될 수 없습니다.");
            return false;
        }
        return true;
    }

    public static boolean isParsableToInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isParsableToLong(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isListAllParsableToInt(List<String> numberList) {
        return numberList.stream().allMatch(GameValidator::isParsableToInt);
    }

    private static boolean isPositivePrice(Long money) {
        return money >= 0;
    }

    private static boolean isOverMinimumLottoPrice(Long money) {
        return money >= LOTTO_PRICE;
    }

    private static boolean isListValidLottoSize(List<Integer> numberList) {
        return numberList.size() == LOTTO_NUM;
    }

    private static boolean isListAllValidLottoNumberRange(List<Integer> numberList) {
        return numberList.stream()
                .allMatch(GameValidator::isValidLottoNumberRange);
    }

    private static boolean isValidLottoNumberRange(int number) {
        return (number >= MIN_LOTTO_NUM) && (number <= MAX_LOTTO_NUM);
    }

    private static boolean isLottoNumbersContainsBonusBall(Lotto lotto, int bonusBall) {
        return lotto.getNumbers().stream().anyMatch(number -> number == bonusBall);
    }

    private static boolean isListDuplicateEachOther(List<Integer> list) {
        HashSet<Integer> hashSet = new HashSet<>(list);
        return hashSet.size() != list.size();
    }
}
