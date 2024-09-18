package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@JdbcTest
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void connect() throws SQLException {
        Connection conection = dataSource.getConnection();
        conection.close();
    }
}
