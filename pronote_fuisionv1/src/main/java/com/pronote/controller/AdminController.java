package com.pronote.controller;

import com.pronote.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminController {

    // === ADD STUDENT ===
    public boolean addStudent(String firstName, String lastName,
                              String email, String password, String filiere) {
        String sql = "INSERT INTO students (first_name, last_name, email, password, filiere, level, age) " +
                "VALUES (?, ?, ?, ?, ?, 1, 18)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, filiere);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE STUDENT ===
    public boolean deleteStudent(String email) {
        String sql = "DELETE FROM students WHERE email = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // === ADD PROFESSOR ===
    public boolean addProfessor(String firstName, String lastName,
                                String email, String password, String subject) {
        String sql = "INSERT INTO professors (first_name, last_name, email, password, subject) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, subject);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE PROFESSOR ===
    public boolean deleteProfessor(String email) {
        String sql = "DELETE FROM professors WHERE email = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
