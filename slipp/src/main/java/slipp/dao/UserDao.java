package slipp.dao;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private static final String INSERT_QUERY = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT userId, password, name, email FROM USERS";
    private static final String SELECT_QUERY = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

    private JdbcTemplate jdbcTemplate = JdbcTemplate.builder()
            .driver("org.h2.Driver")
            .url("jdbc:h2:mem:jwp-framework")
            .userName("sa")
            .password("")
            .build();

    public void insert(User user) {
        jdbcTemplate.execute(INSERT_QUERY, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        jdbcTemplate.execute(UPDATE_QUERY, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        return jdbcTemplate.selectAll(SELECT_ALL_QUERY, this::userMappingStrategy);
    }

    public User findByUserId(String userId) {
        return jdbcTemplate.select(SELECT_QUERY, this::userMappingStrategy, userId);
    }

    private User userMappingStrategy(ResultSet rs) throws SQLException {
        return new User(rs.getString("userId"), rs.getString("password"),
                rs.getString("name"), rs.getString("email"));
    }
}
