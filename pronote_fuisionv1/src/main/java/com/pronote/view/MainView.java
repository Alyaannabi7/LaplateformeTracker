package com.pronote.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import com.pronote.main.MainApp;
import com.pronote.controller.LoginController;

import java.util.Objects;

public class MainView extends StackPane {

    private LoginController controller = new LoginController();

    public MainView() {

        // === BACKGROUND IMAGE ===
        Image bgImage = new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/doorspace.jpg"),
                        "Background image not found"
                )
        );
        ImageView bgView = new ImageView(bgImage);
        bgView.setPreserveRatio(false);
        bgView.fitWidthProperty().bind(this.widthProperty());
        bgView.fitHeightProperty().bind(this.heightProperty());

        // === TOP RED ALERT BOX ===
        Label alertTitle = new Label("Académie Spatial La Plateforme 3.14 ");
        alertTitle.setStyle(
                "-fx-text-fill: black; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold;"
        );

        Label alertSub = new Label("");
        alertSub.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");
        alertSub.setTextAlignment(TextAlignment.CENTER);

        VBox alertBox = new VBox(8, alertTitle, alertSub);
        alertBox.setAlignment(Pos.CENTER);
        alertBox.setPadding(new Insets(20, 40, 20, 40));
        alertBox.setStyle(
                "-fx-background-color: rgba(0, 0, 30, 0.85); " +
                        "-fx-border-color: #ff3355; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-effect: dropshadow(gaussian, #ff0033, 20, 0.8, 0, 0);"
        );

        // === INPUT FIELD STYLE (shared) ===
        String fieldStyle =
                "-fx-background-color: rgba(0, 20, 40, 0.85); " +
                        "-fx-text-fill: #00f7ff; " +
                        "-fx-prompt-text-fill: #336677; " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 4; " +
                        "-fx-background-radius: 4; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10;";

        // === EMAIL FIELD ===
        TextField emailField = new TextField();
        emailField.setPromptText("EMAIL");
        emailField.setMaxWidth(300);
        emailField.setStyle(fieldStyle);

        // === PASSWORD FIELD ===
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setStyle(fieldStyle);

        // === LOGIN BUTTON ===
        Button loginButton = new Button("ENTRER");
        loginButton.setMaxWidth(300);
        loginButton.setStyle(
                "-fx-background-color: rgba(0, 200, 255, 0.15); " +
                        "-fx-text-fill: green; " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 4; " +
                        "-fx-background-radius: 4; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10; " +
                        "-fx-effect: dropshadow(gaussian, #00f7ff, 12, 0.6, 0, 0);"
        );

        // === FEEDBACK MESSAGE LABEL ===
        Label message = new Label();
        message.setStyle("-fx-text-fill: #ff4466; -fx-font-size: 13px;");

        // === LOGIN BUTTON ACTION ===
        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            // Check empty fields
            if (email.isEmpty() || password.isEmpty()) {
                message.setText("⚠️ Please fill in all fields");
                return;
            }

            // Send credentials to controller
            String result = controller.login(email, password);

            // Navigate to the correct panel based on role
            switch (result) {
                case "admin"   -> openPanel("admin");
                case "prof"    -> openPanel("prof");
                case "student" -> openPanel("student");
                case "invalid" -> message.setText("❌ Invalid credentials");
                default        -> message.setText("⚠️ Server error");
            }
        });

        // === FORM BOX ===
        VBox formBox = new VBox(14, emailField, passwordField, loginButton, message);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30, 40, 30, 40));
        formBox.setMaxWidth(380);
        formBox.setStyle(
                "-fx-background-color: rgba(0, 10, 25, 0.75); " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-effect: dropshadow(gaussian, #00f7ff, 18, 0.5, 0, 0);"
        );

        // === MAIN LAYOUT ===
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: transparent;");

        // Alert box pinned to top center
        BorderPane.setAlignment(alertBox, Pos.CENTER);
        BorderPane.setMargin(alertBox, new Insets(60, 200, 0, 200));
        layout.setTop(alertBox);

        // Form box pinned to bottom center
        BorderPane.setAlignment(formBox, Pos.CENTER);
        BorderPane.setMargin(formBox, new Insets(0, 0, 460, 0));
        layout.setBottom(formBox);

        // Stack: background image + layout on top
        this.getChildren().addAll(bgView, layout);
    }

    // === PANEL NAVIGATION — uses MainApp.switchView to keep window size ===
    private void openPanel(String role) {
        TransitionView transition = new TransitionView(role);
        MainApp.switchView(transition, "EDUNAV-1 - Loading...");
    }
}