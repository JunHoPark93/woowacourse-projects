package com.woowacourse.lotto.db.dao;

import com.woowacourse.lotto.db.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDao {
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
}
