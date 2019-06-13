package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.LottoBuyDao;
import com.woowacourse.lotto.domain.LottoBuyList;

import java.sql.SQLException;

public class LottoBuyWebService extends LottoBuyService {
    private int round;

    public LottoBuyWebService(String moneyInput, String manualNumberInput, String[] manualLottos, int round) {
        super(moneyInput, manualNumberInput, manualLottos);
        this.round = round;
    }

    @Override
    public LottoBuyList createTotalBuyList() {
        LottoBuyList totalBuys = super.createTotalBuyList();
        addBuys(totalBuys);
        return totalBuys;
    }

    private void addBuys(LottoBuyList totalBuys) {
        try {
            LottoBuyDao.addBuys(totalBuys, round);
        } catch (SQLException e) {
            System.out.println("구매목록 삽입 에러");
            System.exit(-1);
        }
    }
}
