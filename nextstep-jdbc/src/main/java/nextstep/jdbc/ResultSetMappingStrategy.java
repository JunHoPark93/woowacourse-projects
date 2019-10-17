package nextstep.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetMappingStrategy<T> {
    T map(ResultSet resultSet) throws SQLException;
}

