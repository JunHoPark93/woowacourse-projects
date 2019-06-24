package chess.domain.db;

import chess.db.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionTest {
    private ConnectionFactory connectionFactory;

    @BeforeEach
    public void setup() {
        connectionFactory = new ConnectionFactory();
    }

    @Test
    public void connection() throws SQLException {
        Connection con = connectionFactory.getConnection();
        assertNotNull(con);
    }
}
