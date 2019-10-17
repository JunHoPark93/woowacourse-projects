package nextstep.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetParameterStrategy {
    void setParams(PreparedStatement psmt) throws SQLException;
}
