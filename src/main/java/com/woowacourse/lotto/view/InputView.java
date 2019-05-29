package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.util.InputUtil;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseMoney getMoneyFromUser() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkPurchaseMoneyInput(input);

            return new PurchaseMoney(Integer.parseInt(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMoneyFromUser();
        }
    }

    public static Lotto getLastWeekLottoFromUser() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkWinningLottoInput(input);

            return InputUtil.parseStringToLotto(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLastWeekLottoFromUser();
        }
    }

    public static LottoNumber getBonusNumberFromUser() {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkBonusBallInput(input);

            return null;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumberFromUser();
        }
    }
}
