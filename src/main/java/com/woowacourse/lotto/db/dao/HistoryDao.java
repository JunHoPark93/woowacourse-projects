package com.woowacourse.lotto.db.dao;

import com.woowacourse.lotto.db.ConnectionFactory;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.LottoParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    public static History selectHistory(String round) throws SQLException {
        LottoBuyList lottoBuyList = selectLottos(round);
        Winning winning = selectWinningLotto(round);
        Lotto lotto = winning.getLotto();
        BonusNumber bonusNumber = winning.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottoBuyList, winningLotto);
        PurchaseMoney purchaseMoney = new PurchaseMoney(String.valueOf(lottoBuyList.price()));

        return new History(lottoBuyList, lottoResult, purchaseMoney, lotto, bonusNumber);
    }

    private static Winning selectWinningLotto(String round) throws SQLException {
        String query = "SELECT winning_num, bonus_num FROM W_LOTTO WHERE round_id = ?";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new RuntimeException("no round");
        }

        String lottoStr = rs.getString("winning_num");
        String bonusNumStr = rs.getString("bonus_num");
        Lotto lotto = LottoParser.parseStringToLotto(lottoStr);
        BonusNumber bonusNumber = new BonusNumber(bonusNumStr, lotto);

        return new Winning(lotto, bonusNumber);
    }

    private static LottoBuyList selectLottos(String round) throws SQLException {
        String query = "SELECT * FROM BUY_HISTORY WHERE round_id = ?";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet rs = pstmt.executeQuery();
        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            String lottoStr = rs.getString("lotto");
            lottos.add(LottoParser.parseStringToLotto(lottoStr));
        }

        return new LottoBuyList(lottos);
    }

    private static class Winning {
        private Lotto lotto;
        private BonusNumber bonusNumber;

        public Winning(Lotto lotto, BonusNumber bonusNumber) {
            this.lotto = lotto;
            this.bonusNumber = bonusNumber;
        }

        public Lotto getLotto() {
            return lotto;
        }

        public BonusNumber getBonusNumber() {
            return bonusNumber;
        }
    }
}
