package com.pronote.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // PostgreSQL connection settings
    private static final String URL      = "jdbc:postgresql://localhost:5432/pronote_db";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "azertyDU06!123456789"; // ← ton mot de passe

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}