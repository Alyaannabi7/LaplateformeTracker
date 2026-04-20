package com.pronote.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StudentPanel extends VBox {

    public StudentPanel() {
        this.getStyleClass().add("panel");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(40));
        this.setPrefWidth(900);

        Label title = new Label("STUDENT DASHBOARD");
        title.getStyleClass().add("student-title");
        title.setFont(Font.font("Orbitron", FontWeight.BOLD, 32));

        Label sub = new Label("Coming soon...");
        sub.getStyleClass().add("student-sub");

        this.getChildren().addAll(title, sub);
    }
}