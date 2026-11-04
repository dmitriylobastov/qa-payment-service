package ru.stepup.payments.pages;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import java.sql.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTest {
    private static PostgreSQLContainer<?> postgre;
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                postgre.getJdbcUrl(),
                postgre.getUsername(),
                postgre.getPassword()
        );
    }

    @BeforeAll
    static void setUpAll() throws SQLException {
        postgre = new PostgreSQLContainer<>("postgres:15-alpine");
        postgre.start();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(50));");
        }
    }

    @BeforeEach
    void setUp() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("TRUNCATE TABLE users");
        }
    }

    @AfterAll
    static void tearDownAll() {
        if (postgre != null) {
            postgre.stop();
        }
    }

    @Test
    void shouldDbConnect() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO users (name) VALUES ('Test User');");
            try (ResultSet rs = stmt.executeQuery("SELECT name FROM users")) {
                rs.next();
                String name = rs.getString("name");
                assertThat(name).isEqualTo("Test User");
            }
        }
    }
}
