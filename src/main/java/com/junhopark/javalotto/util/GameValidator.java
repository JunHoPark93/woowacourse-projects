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

    public static boolean isPositivePrice(Long money) {
        return money >= 0;
    }

    public static boolean isOverMinimumLottoPrice(Long money) {
        return money >= LOTTO_PRICE;
    }

    public static boolean isListValidLottoSize(List<Integer> numberList) {
        return numberList.size() == LOTTO_NUM;
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

    public static boolean isParsableToInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isListAllValidLottoNumberRange(List<Integer> numberList) {
        return numberList.stream()
                .allMatch(GameValidator::isValidLottoNumberRange);
    }

    public static boolean isValidLottoNumberRange(int number) {
        return (number >= MIN_LOTTO_NUM) && (number <= MAX_LOTTO_NUM);
    }

    public static boolean isLottoNumbersContainsBonusBall(Lotto lotto, int bonusBall) {
        return lotto.getNumbers().stream().anyMatch(number -> number == bonusBall);
    }

    public static boolean isListDuplicateEachOther(List<Integer> list) {
        HashSet<Integer> hashSet = new HashSet<>(list);
        return hashSet.size() == list.size();
    }
}
