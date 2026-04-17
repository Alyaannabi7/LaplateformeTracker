package com.pronote.controller;

import com.pronote.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public String login(String email, String password) {

        try (Connection conn = DatabaseConnection.connect()) {

            // Check admin table
            String sqlAdmin = "SELECT * FROM admins WHERE email = ? AND password = ?";
            PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
            stmtAdmin.setString(1, email);
            stmtAdmin.setString(2, password);
            ResultSet rsAdmin = stmtAdmin.executeQuery();
            if (rsAdmin.next()) return "admin";

            // Check professor table
            String sqlProf = "SELECT * FROM professors WHERE email = ? AND password = ?";
            PreparedStatement stmtProf = conn.prepareStatement(sqlProf);
            stmtProf.setString(1, email);
            stmtProf.setString(2, password);
            ResultSet rsProf = stmtProf.executeQuery();
            if (rsProf.next()) return "prof";

            // Check student table
            String sqlStudent = "SELECT * FROM students WHERE email = ? AND password = ?";
            PreparedStatement stmtStudent = conn.prepareStatement(sqlStudent);
            stmtStudent.setString(1, email);
            stmtStudent.setString(2, password);
            ResultSet rsStudent = stmtStudent.executeQuery();
            if (rsStudent.next()) return "student";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "invalid";
    }
}