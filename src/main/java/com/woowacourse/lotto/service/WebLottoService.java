package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.db.dao.LottoDao;

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
            LottoDao.addBuys(totalBuys);
        } catch (SQLException e) {
            System.out.println("구매목록 삽입 에러");
            System.exit(-1);
        }
    }

    @Override
    public LottoResult createResult(LottoBuyList totalBuys, Lotto lastWeekLotto, LottoNumber bonusNumber) {
        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, bonusNumber);
        addWinningLotto(lastWeekLotto, bonusNumber);

        return new LottoResult(totalBuys, winningLotto);
    }

    private void addWinningLotto(Lotto lastWeekLotto, LottoNumber bonusNumber) {
        try {
            LottoDao.addWinningLotto(lastWeekLotto, bonusNumber);
        } catch (SQLException e) {
            System.out.println("결과 삽입 에러");
            System.exit(-1);
        }
    }

    public History createHistory(String round) throws SQLException {
        return LottoDao.selectHistory(round);
    }
}
