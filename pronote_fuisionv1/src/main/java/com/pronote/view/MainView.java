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

        // Background image
        Image bgImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/doorspace.jpg"),
                "Background image not found"
        ));
        ImageView bgView = new ImageView(bgImage);
        bgView.setPreserveRatio(false);
        bgView.fitWidthProperty().bind(this.widthProperty());
        bgView.fitHeightProperty().bind(this.heightProperty());

        // Alert box
        Label alertTitle = new Label("Académie Spatial La Plateforme");
        alertTitle.getStyleClass().add("alert-title");

        Label alertSub = new Label("");
        alertSub.setTextAlignment(TextAlignment.CENTER);

        VBox alertBox = new VBox(8, alertTitle, alertSub);
        alertBox.setAlignment(Pos.CENTER);
        alertBox.setPadding(new Insets(20, 40, 20, 40));
        alertBox.getStyleClass().add("alert-box");

        // Email field
        TextField emailField = new TextField();
        emailField.setPromptText("EMAIL");
        emailField.setMaxWidth(300);
        emailField.getStyleClass().add("field-cyan");

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.getStyleClass().add("field-cyan");

        // Login button
        Button loginButton = new Button("ENTRER");
        loginButton.setMaxWidth(300);
        loginButton.getStyleClass().add("btn-login");

        // Feedback
        Label message = new Label();
        message.getStyleClass().add("feedback-error");

        // Login action
        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            if (email.isEmpty() || password.isEmpty()) {
                message.setText("⚠️ Please fill in all fields");
                return;
            }

            String result = controller.login(email, password);

            switch (result) {
                case "admin"   -> openPanel("admin");
                case "prof"    -> openPanel("prof");
                case "student" -> openPanel("student");
                case "invalid" -> message.setText("❌ Invalid credentials");
                default        -> message.setText("⚠️ Server error");
            }
        });

        // Form box
        VBox formBox = new VBox(14, emailField, passwordField, loginButton, message);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30, 40, 30, 40));
        formBox.setMaxWidth(380);
        formBox.getStyleClass().add("form-box");

        // Layout
        BorderPane layout = new BorderPane();
        layout.getStyleClass().add("layout-transparent");

        BorderPane.setAlignment(alertBox, Pos.CENTER);
        BorderPane.setMargin(alertBox, new Insets(60, 200, 0, 200));
        layout.setTop(alertBox);

        BorderPane.setAlignment(formBox, Pos.CENTER);
        BorderPane.setMargin(formBox, new Insets(0, 0, 460, 0));
        layout.setBottom(formBox);

        this.getChildren().addAll(bgView, layout);
    }

    private void openPanel(String role) {
        TransitionView transition = new TransitionView(role);
        MainApp.switchView(transition, "EDUNAV-1 - Loading...");
    }
}