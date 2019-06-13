package com.woowacourse.lotto.db.dao;

import com.woowacourse.lotto.db.ConnectionFactory;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
    public static int selectRound() throws SQLException {
        String query = "SELECT MAX(id) FROM ROUND;";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) {
            throw new RuntimeException("no round");
        }

        String lastRound = resultSet.getString("max(id)");
        return Integer.valueOf(lastRound);
    }

    public static void addRound(int round) throws SQLException {
        String query = "INSERT INTO ROUND(id) VALUES (?)";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, String.valueOf(round + 1));
        pstmt.executeUpdate();
    }

    public static void addBuys(LottoBuyList totalBuys) throws SQLException {
        int round = selectRound();

        for (int i = 0; i < totalBuys.size(); i++) {
            String query = "INSERT INTO BUY_HISTORY(user_id, round_id, lotto) VALUES (?, ?, ?)";
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
            pstmt.setString(1, "1");
            pstmt.setString(2, String.valueOf(round));
            pstmt.setString(3, removeBraces(totalBuys.getLotto(i)));
            pstmt.executeUpdate();
        }
    }

    private static String removeBraces(LottoTicket lotto) {
        return lotto.toString().replace("[", "").replace("]", "");
    }

    public static void addWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) throws SQLException {
        int round = selectRound();

        String query = "INSERT INTO W_LOTTO(round_id, winning_num, bonus_num) VALUES (?, ?, ?)";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, String.valueOf(round));
        pstmt.setString(2, String.valueOf(removeBraces(winningLotto)));
        pstmt.setString(3, String.valueOf(bonusNumber.getLottoNum()));

        pstmt.executeUpdate();
    }

    public static History selectHistory(String round) throws SQLException {
        LottoBuyList lottoBuyList = selectLottos(round);
        Winning winning = selectWinningLotto(round);
        Lotto lotto = winning.getLotto();
        LottoNumber bonusNumber = winning.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottoBuyList, winningLotto);
        PurchaseMoney purchaseMoney = new PurchaseMoney(lottoBuyList.price());

        return new History(lottoBuyList, lottoResult, purchaseMoney, lotto, bonusNumber);
    }

    private static Winning selectWinningLotto(String round) throws SQLException {
        String query = "SELECT * FROM W_LOTTO WHERE round_id = ?";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new RuntimeException("no round");
        }

        String lottoStr = rs.getString("winning_num");
        String bonusNumStr = rs.getString("bonus_num");
        Lotto lotto = LottoService.createLotto(lottoStr);
        LottoNumber bonusNumber = LottoService.createBonusNumber(bonusNumStr, lotto);

        return new Winning(lotto, bonusNumber);
    }

    private static LottoBuyList selectLottos(String round) throws SQLException {
        String query = "SELECT * FROM BUY_HISTORY WHERE round_id = ?";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet rs = pstmt.executeQuery();
        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            String lotto = rs.getString("lotto");
            lottos.add(LottoService.createLotto(lotto));
        }

        return new LottoBuyList(lottos);
    }

    private static class Winning {
        private Lotto lotto;
        private LottoNumber bonusNumber;

        public Winning(Lotto lotto, LottoNumber bonusNumber) {
            this.lotto = lotto;
            this.bonusNumber = bonusNumber;
        }

        public Lotto getLotto() {
            return lotto;
        }

        public LottoNumber getBonusNumber() {
            return bonusNumber;
        }
    }
}
