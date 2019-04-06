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

public class GameValidator {
    private static final int LOTTO_PRICE = 1000;

    public static boolean isPurchaseMoneyInputValid(String input) {
        if (!isParsableToLong(input)) {
            return false;
        }
        Long money = Long.parseLong(input);
        return isPositivePrice(money) && isOverMinimumLottoPrice(money);
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

    private static boolean isParsableToLong(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("입력 범위를 벗어났습니다.");
            return false;
        }
        return true;
    }
}
