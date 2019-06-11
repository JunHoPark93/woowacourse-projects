package com.woowacourse.lotto.domain.repository;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoNumber;

import java.sql.*;
import java.util.List;

public class LottoRepository {
    public static Connection getConnection() {
        Connection con = null;
        String server = "54.180.121.70"; // MySQL 서버 주소
        String database = "mydb"; // MySQL DATABASE 이름
        String userName = "jay"; //  MySQL 서버 아이디
        String password = "O/pnjhp2%c!c"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public static int selectRound() throws SQLException {
        String query = "SELECT MAX(id) FROM ROUND;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) {
            throw new RuntimeException("no round");
        }

        String lastRound = resultSet.getString("max(id)");
        return Integer.valueOf(lastRound);
    }

    public static void addRound(int round) throws SQLException {
        String query = "INSERT INTO ROUND(id) VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, String.valueOf(round + 1));
        pstmt.executeUpdate();
    }

    public static void addBuys(LottoBuyList totalBuys) throws SQLException {
        List<Lotto> lottos = totalBuys.getLottoBuyList();
        int round = selectRound();
        for (Lotto lotto : lottos) {
            String query = "INSERT INTO BUY_HISTORY(user_id, round_id, lotto) VALUES (?, ?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, "1");
            pstmt.setString(2, String.valueOf(round));
            pstmt.setString(3, lotto.toString());
            pstmt.executeUpdate();
        }
    }

    public static void addWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) throws SQLException {
        int round = selectRound();

        String query = "INSERT INTO W_LOTTO(round_id, winning_num, bonus_num) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, String.valueOf(round));
        pstmt.setString(2, String.valueOf(winningLotto));
        pstmt.setString(3, String.valueOf(bonusNumber.getLottoNum()));

        pstmt.executeUpdate();
    }
}
