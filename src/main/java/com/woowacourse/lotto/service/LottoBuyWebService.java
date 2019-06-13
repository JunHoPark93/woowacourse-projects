package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.LottoDao;
import com.woowacourse.lotto.domain.LottoBuyList;

import java.sql.SQLException;

public class LottoBuyWebService extends LottoBuyService {
    public LottoBuyWebService(String moneyInput, String manualNumberInput, String[] manualLottos) {
        super(moneyInput, manualNumberInput, manualLottos);
    }

    @Override
    public LottoBuyList createTotalBuyList() {
        LottoBuyList totalBuys = super.createTotalBuyList();
        addBuys(totalBuys);
        return totalBuys;
    }

    private void addBuys(LottoBuyList totalBuys) {
        try {
            LottoDao.addBuys(totalBuys);
        } catch (SQLException e) {
            System.out.println("구매목록 삽입 에러");
            System.exit(-1);
        }
    }
}
