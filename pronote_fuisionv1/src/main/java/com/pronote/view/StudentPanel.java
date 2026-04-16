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

        // Title
        Label title = new Label("STUDENT DASHBOARD");
        title.setFont(Font.font("Orbitron", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill: #00f7ff; " +
                "-fx-effect: dropshadow(gaussian, #00f7ff, 25, 0.9, 0, 0);");

        // Placeholder
        Label sub = new Label("Coming soon...");
        sub.setStyle("-fx-text-fill: #88ddff; -fx-font-size: 16px;");

        this.getChildren().addAll(title, sub);
    }
}
