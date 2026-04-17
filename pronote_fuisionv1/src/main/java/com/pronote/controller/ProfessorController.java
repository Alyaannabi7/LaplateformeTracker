package com.pronote.controller;

import com.pronote.database.DatabaseConnection;
import com.pronote.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfessorController {

    public ObservableList<Student> getStudentsByLevel(int level) {
        ObservableList<Student> list = FXCollections.observableArrayList();

        String sql =
                "SELECT s.first_name, s.last_name, s.age, s.filiere, " +
                        "       (SELECT g.grade FROM grades g " +
                        "        WHERE g.student_id = s.id " +
                        "        ORDER BY g.project_id ASC LIMIT 1) AS first_grade " +
                        "FROM students s " +
                        "WHERE s.level = ? " +
                        "ORDER BY s.last_name ASC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, level);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        String.valueOf(rs.getInt("age")),
                        rs.getString("filiere"),
                        String.format("%.2f", rs.getDouble("first_grade"))
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}