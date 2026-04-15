package pronote.controller;

import pronote.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public String login(String email, String password) {

        try (Connection conn = DatabaseConnection.connect()) {

            // Check student table
            String sqlStudent = "SELECT * FROM students WHERE email = ? AND password = ?";
            PreparedStatement stmtStudent = conn.prepareStatement(sqlStudent);
            stmtStudent.setString(1, email);
            stmtStudent.setString(2, password);
            ResultSet rsStudent = stmtStudent.executeQuery();
            if (rsStudent.next()) return "student";

            // Check professor table
            String sqlProf = "SELECT * FROM professors WHERE email = ? AND password = ?";
            PreparedStatement stmtProf = conn.prepareStatement(sqlProf);
            stmtProf.setString(1, email);
            stmtProf.setString(2, password);
            ResultSet rsProf = stmtProf.executeQuery();
            if (rsProf.next()) return "prof";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        // No match found
        return "invalid";
    }
}