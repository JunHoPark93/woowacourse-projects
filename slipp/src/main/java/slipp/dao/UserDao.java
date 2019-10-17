package slipp.dao;

import nextstep.jdbc.JdbcTemplate;
import nextstep.jdbc.exception.UserNotFoundException;
import slipp.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static final String INSERT_QUERY = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT userId, password, name, email FROM USERS";
    private static final String SELECT_QUERY = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(User user) {
        jdbcTemplate.executeQuery(INSERT_QUERY, psmt -> {userInsertParamStrategy(user, psmt);});
    }

    public void update(User user) {
        jdbcTemplate.executeQuery(UPDATE_QUERY, psmt -> {userUpdateParamStrategy(user, psmt);});
    }

    public List<User> findAll() {
        Optional<List<User>> users = jdbcTemplate.executeQuery(SELECT_ALL_QUERY, this::userMappingStrategy);
        return users.orElse(Collections.emptyList());
    }

    public User findByUserId(String userId) {
        Optional<List<User>> users = jdbcTemplate.executeQuery(SELECT_QUERY, psmt -> findSingleUserParamStrategy(userId, psmt), this::userMappingStrategy);
        return users.orElseThrow(UserNotFoundException::new).stream()
                .findFirst().orElseThrow(UserNotFoundException::new);
    }

    private User userMappingStrategy(ResultSet rs) throws SQLException {
        return new User(rs.getString("userId"), rs.getString("password"),
                rs.getString("name"), rs.getString("email"));
    }

    private void userInsertParamStrategy(User user, PreparedStatement psmt) throws SQLException {
        psmt.setString(1, user.getUserId());
        psmt.setString(2, user.getPassword());
        psmt.setString(3, user.getName());
        psmt.setString(4, user.getEmail());
    }

    private void userUpdateParamStrategy(User user, PreparedStatement psmt) throws SQLException {
        psmt.setString(1, user.getPassword());
        psmt.setString(2, user.getName());
        psmt.setString(3, user.getEmail());
        psmt.setString(4, user.getUserId());
    }

    private void findSingleUserParamStrategy(String userId, PreparedStatement psmt) throws SQLException {
        psmt.setString(1, userId);
    }
}
