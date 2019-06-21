package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.DrawDao;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.LottoParser;

import java.sql.SQLException;

public class DrawWebService implements DrawService {
    private LottoBuyList totalBuys;
    private Lotto lastWeekLotto;
    private BonusNumber bonusNumber;
    private int round;

    public DrawWebService(LottoBuyList totalBuys, String lottoInput, String bonusNumberInput, int round) {
        Lotto lastWeekLotto = LottoParser.parseStringToLotto(lottoInput);
        BonusNumber bonusNumber = new BonusNumber(bonusNumberInput, lastWeekLotto);

        this.totalBuys = totalBuys;
        this.lastWeekLotto = lastWeekLotto;
        this.bonusNumber = bonusNumber;
        this.round = round;
    }

    @Override
    public LottoResult createResult() {
        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, bonusNumber);
        LottoResult lottoResult = new LottoResult(totalBuys, winningLotto);
        addWinningLotto(lastWeekLotto, bonusNumber);
        return lottoResult;
    }

    private void addWinningLotto(Lotto lastWeekLotto, BonusNumber bonusNumber) {
        try {
            DrawDao.addWinningLotto(lastWeekLotto, bonusNumber, round);
        } catch (SQLException e) {
            System.out.println("결과 삽입 에러");
            System.exit(-1);
        }
    }
}
