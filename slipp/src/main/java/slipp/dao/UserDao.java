package slipp.dao;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;

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
        jdbcTemplate.executeQuery(INSERT_QUERY, null, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        jdbcTemplate.executeQuery(UPDATE_QUERY, null, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        Optional<List<User>> users = jdbcTemplate.executeQuery(SELECT_ALL_QUERY, this::userMappingStrategy);
        return users.orElse(Collections.emptyList());
    }

    public User findByUserId(String userId) {
        Optional<List<User>> users = jdbcTemplate.executeQuery(SELECT_QUERY, this::userMappingStrategy, userId);
        return users.orElse(Collections.emptyList()).get(0);
    }

    private User userMappingStrategy(ResultSet rs) throws SQLException {
        return new User(rs.getString("userId"), rs.getString("password"),
                rs.getString("name"), rs.getString("email"));
    }
}
