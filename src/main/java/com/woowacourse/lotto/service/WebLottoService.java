package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.repository.LottoRepository;

import java.sql.SQLException;

public class WebLottoService extends LottoService {
    @Override
    public LottoBuyList createTotalBuyList(LottoBuyList manualBuys, PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        LottoBuyList autoBuys = super.getAutoLottoBuyList(purchaseMoney, manualNumber);
        LottoBuyList totalBuys = autoBuys.joinBuyList(manualBuys);
        addBuys(totalBuys);

        return autoBuys.joinBuyList(manualBuys);
    }

    private void addBuys(LottoBuyList totalBuys) {
        try {
            LottoRepository.addBuys(totalBuys);
        } catch (SQLException e) {
            System.out.println("구매목록 삽입 에러");
            System.exit(-1);
        }
    }

    @Override
    public LottoResult createResult(LottoBuyList totalBuys, WinningLotto winningLotto) {
        addWinningLotto(winningLotto);

        return new LottoResult(totalBuys, winningLotto);
    }

    private void addWinningLotto(WinningLotto winningLotto) {
        try {
            LottoRepository.addWinningLotto(winningLotto.getLotto(), winningLotto.getBonusNumber());
        } catch (SQLException e) {
            System.out.println("결과 삽입 에러");
            System.exit(-1);
        }
    }
}
