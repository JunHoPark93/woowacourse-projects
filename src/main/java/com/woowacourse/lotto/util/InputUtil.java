package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtil {
    private static final Pattern PURCHASE_MONEY_PATTERN = Pattern.compile("^[0-9]*$");
    private static final Pattern SINGLE_LOTTO_NUMBER_PATTERN = Pattern.compile("[0-9]*");
    private static final Pattern WINNING_LOTTO_PATTERN =
            Pattern.compile("("+SINGLE_LOTTO_NUMBER_PATTERN + ", ){5}" + SINGLE_LOTTO_NUMBER_PATTERN);

    public static void checkPurchaseMoneyInput(String input) {
        Matcher matcher = PURCHASE_MONEY_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("구입 금액 입력이 잘못되었습니다");
        }
    }

    public static void checkWinningLottoInput(String input) {
        Matcher matcher = WINNING_LOTTO_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("당첨 번호 입력이 잘못 되었습니다");
        }
    }

    public static Lotto parseStringToLotto(String input) {
        String[] tokens = input.split(",");
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (String token : tokens) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(token.trim())));
        }

        return new Lotto(lottoNumbers);
    }
}
