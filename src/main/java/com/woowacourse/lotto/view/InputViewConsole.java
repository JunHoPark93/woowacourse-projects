package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.LottoParser;

import java.util.Collections;

public class InputViewConsole {
    public BonusNumber inputBonusNumber(Lotto lastWeekLotto) {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            return new BonusNumber(Input.inputString(), lastWeekLotto);
        } catch (IllegalArgumentException e) {
            return inputBonusNumber(lastWeekLotto);
        }
    }

    public Lotto inputLastWeekLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            return LottoParser.parseStringToLotto(Input.inputString());
        } catch (IllegalArgumentException e) {
            return inputLastWeekLotto();
        }
    }

    public LottoBuyList inputManualBuys(ManualNumber manualNumber) {
        if (manualNumber.isEmpty()) {
            return new LottoBuyList(Collections.emptyList());
        }
        LottoBuyList lottoBuyList = new LottoBuyList(Collections.emptyList());
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            for (int i = 0; i < manualNumber.getNum(); i++) {
                lottoBuyList.addManualLotto(LottoParser.parseStringToLotto(Input.inputString()));
                return lottoBuyList;
            }
        } catch (IllegalArgumentException e) {
            return inputManualBuys(manualNumber);
        }
        throw new RuntimeException("수동 구매 에러");
    }

    public ManualNumber inputManualNumber(PurchaseMoney purchaseMoney) {
        try {
            System.out.println(OutputViewConsole.inputManualNumberMsg());
            return new ManualNumber(Input.inputString(), purchaseMoney);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력");
            return inputManualNumber(purchaseMoney);
        }
    }

    public PurchaseMoney inputPurchaseMoney() {
        try {
            System.out.println(OutputViewConsole.inputPurchaseMoneyMsg());
            return new PurchaseMoney(Input.inputString());
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력");
            return inputPurchaseMoney();
        }
    }
}
