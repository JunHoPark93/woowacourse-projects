package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.Lotto;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputUtil {
    private static final Pattern PURCHASE_MONEY_PATTERN = Pattern.compile("\\d+");
    private static final Pattern SINGLE_LOTTO_NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern MANUAL_LOTTO_NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern LOTTO_PATTERN =
            Pattern.compile("(" + SINGLE_LOTTO_NUMBER_PATTERN + ",\\s){5}" + SINGLE_LOTTO_NUMBER_PATTERN);

    public static void checkPurchaseMoneyInput(String input) {
        Matcher matcher = PURCHASE_MONEY_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("구입 금액 입력이 잘못되었습니다");
        }
    }

    public static void checkLottoInput(String input) {
        Matcher matcher = LOTTO_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("당첨 번호 입력이 잘못 되었습니다");
        }
    }

    public static Lotto parseStringToLotto(String input) {
        String[] tokens = input.split(",");

        List<Integer> lottoNumbers = Arrays.stream(tokens)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(lottoNumbers);

        return new Lotto(intendedLottoGenerator);
    }

    public static void checkBonusBallInput(String input) {
        Matcher matcher = SINGLE_LOTTO_NUMBER_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("보너스 볼 입력이 잘못 되었습니다");
        }
    }

    public static void checkManualNumber(String input) {
        Matcher matcher = MANUAL_LOTTO_NUMBER_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("구매 개수 입력이 잘못 되었습니다");
        }
    }
}
