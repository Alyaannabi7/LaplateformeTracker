package com.pronote.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

    private static final String URL      = "jdbc:postgresql://localhost:5432/pronote_db";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "azertyDU06!123456789";

    public static Connection connect() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        props.setProperty("clientEncoding", "UTF-8");
        props.setProperty("characterEncoding", "UTF-8");

        Connection conn = DriverManager.getConnection(URL, props);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("SET client_encoding TO 'UTF8'");
        }

        return conn;
    }
}