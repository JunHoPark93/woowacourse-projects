package slipp.dao;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;
import slipp.support.db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String INSERT_QUERY = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT userId, password, name, email FROM USERS";
    private static final String SELECT_QUERY = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

    private Connection con;
    private PreparedStatement pstmt;

    public void insert(User user) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.builder()
                .driver("org.h2.Driver")
                .url("jdbc:h2:mem:jwp-framework")
                .userName("sa")
                .password("")
                .build();
        jdbcTemplate.insert(INSERT_QUERY, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) throws SQLException {
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(UPDATE_QUERY);
            setUpdateParameters(user);
            pstmt.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public List<User> findAll() throws SQLException {
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = pstmt.executeQuery();

            return getUsers(resultSet);
        } finally {
            closeResources();
        }
    }

    public User findByUserId(String userId) throws SQLException {
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            return getUser(rs);

        } finally {
            closeResources(rs);
        }
    }

    private void setUpdateParameters(User user) throws SQLException {
        pstmt.setString(1, user.getPassword());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getUserId());
    }

    private List<User> getUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String userId = resultSet.getString("userId");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            users.add(new User(userId, password, name, email));
        }
        return users;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                    rs.getString("email"));
        }
        return user;
    }

    private void closeResources() throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (con != null) {
            con.close();
        }
    }

    private void closeResources(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (con != null) {
            con.close();
        }
    }

    private void setInsertParameters(User user) throws SQLException {
        pstmt.setString(1, user.getUserId());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
    }
}
