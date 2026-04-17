package com.pronote.view;

import com.pronote.controller.AdminController;
import com.pronote.main.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class AdminPanel extends VBox {

    private final AdminController controller = new AdminController();

    public AdminPanel() {
        setStyle();
        createHeader();
        createMainContent();
        createBottomButtons();
    }

    private void setStyle() {
        this.getStyleClass().add("panel");
        this.setPrefWidth(1150);
        this.setPrefHeight(650);
        this.setMaxWidth(1150);
        this.setSpacing(30);
        this.setPadding(new Insets(35));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle(
                "-fx-background-color: rgba(8, 25, 48, 0.15); " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 3px; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-effect: dropshadow(gaussian, #00f7ff, 35, 0.75, 0, 0);"
        );
    }

    private void createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        VBox adminInfo = new VBox(6);
        adminInfo.setAlignment(Pos.CENTER_LEFT);

        Label adminTitle = new Label("ADMIN");
        adminTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 34));
        adminTitle.setStyle("-fx-text-fill: #00cc66; -fx-effect: dropshadow(gaussian, #00f7ff, 25, 0.9, 0, 0);");

        Label nameLabel = new Label("- NOM: MR");
        Label preLabel  = new Label("- PRENOM: HEY");
        nameLabel.setStyle("-fx-text-fill: #b0f8ff; -fx-font-size: 17px;");
        preLabel.setStyle("-fx-text-fill: #b0f8ff; -fx-font-size: 17px;");

        adminInfo.getChildren().addAll(adminTitle, nameLabel, preLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Menu button — back to login
        Button menuButton = new Button("Menu");
        menuButton.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-padding: 12 40; " +
                        "-fx-background-color: #0000cc; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 2;"
        );
        menuButton.setOnAction(e -> javafx.application.Platform.runLater(() -> {
            MainApp.getScene().getStylesheets().clear();
            MainApp.getScene().getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/styles/neon-dashboard.css")
                    ).toExternalForm()
            );
            MainApp.switchView(new MainView(), "EDUNAV-1 - Secure Access");
        }));

        header.getChildren().addAll(adminInfo, spacer, menuButton);
        this.getChildren().add(header);
    }

    private void createMainContent() {
        HBox mainContent = new HBox(50);
        mainContent.setAlignment(Pos.CENTER);

        // Left - Profs panel with DB actions
        VBox profsPanel = createManagementPanel("Profs:", true);

        // Center - Search area
        VBox centerArea = new VBox(30);
        centerArea.setAlignment(Pos.TOP_CENTER);

        Region topSpacer = new Region();
        topSpacer.setPrefHeight(40);

        Button searchButton = new Button("Recherche");
        searchButton.setStyle("-fx-font-size: 22px; -fx-padding: 15 80;");

        VBox resultsArea = new VBox(10);
        resultsArea.setPrefHeight(280);
        resultsArea.setStyle(
                "-fx-background-color: rgba(255,255,255,0.03); " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 12;"
        );

        centerArea.getChildren().addAll(topSpacer, searchButton, resultsArea);

        // Right - Students panel with DB actions
        VBox studentsPanel = createManagementPanel("Eleves:", false);

        mainContent.getChildren().addAll(profsPanel, centerArea, studentsPanel);
        this.getChildren().add(mainContent);
    }

    private VBox createManagementPanel(String title, boolean isProf) {
        VBox panel = new VBox(12);
        panel.setPrefWidth(320);
        panel.setStyle(
                "-fx-background-color: rgba(8, 25, 48, 0.92); " +
                        "-fx-padding: 25; " +
                        "-fx-border-radius: 16;"
        );

        Label panelTitle = new Label(title);
        panelTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 19));
        panelTitle.setStyle("-fx-text-fill: #00f7ff;");
        panel.getChildren().add(panelTitle);

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("prénom");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("nom");

        TextField emailField = new TextField();
        emailField.setPromptText("mail");

        TextField passwordField = new TextField();
        passwordField.setPromptText("mot de passe");

        TextField subjectField = new TextField();
        subjectField.setPromptText(isProf ? "matière enseignée" : "filière");

        String fieldStyle =
                "-fx-background-color: rgba(255,255,255,0.07); " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: #777777;";

        firstNameField.setStyle(fieldStyle);
        lastNameField.setStyle(fieldStyle);
        emailField.setStyle(fieldStyle);
        passwordField.setStyle(fieldStyle);
        subjectField.setStyle(fieldStyle);

        panel.getChildren().addAll(
                firstNameField, lastNameField,
                emailField, passwordField, subjectField
        );

        // Feedback label
        Label feedback = new Label();
        feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #00f7ff;");
        panel.getChildren().add(feedback);

        // Action buttons
        HBox actionButtons = new HBox(15);
        actionButtons.setAlignment(Pos.CENTER);

        Button addBtn    = new Button("Ajouter");
        Button deleteBtn = new Button("Supprimer");

        addBtn.setStyle("-fx-background-color: #00cc66; -fx-text-fill: white; -fx-padding: 8 20;");
        deleteBtn.setStyle("-fx-background-color: #ff3366; -fx-text-fill: white; -fx-padding: 8 20;");

        // Add action
        addBtn.setOnAction(e -> {
            String fn  = firstNameField.getText().trim();
            String ln  = lastNameField.getText().trim();
            String em  = emailField.getText().trim();
            String pw  = passwordField.getText().trim();
            String sub = subjectField.getText().trim();

            // Validate fields
            if (fn.isEmpty() || ln.isEmpty() || em.isEmpty() || pw.isEmpty() || sub.isEmpty()) {
                feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #ff4466;");
                feedback.setText("⚠️ Remplis tous les champs");
                return;
            }

            boolean success = isProf
                    ? controller.addProfessor(fn, ln, em, pw, sub)
                    : controller.addStudent(fn, ln, em, pw, sub);

            if (success) {
                feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #00cc66;");
                feedback.setText("✅ Ajouté avec succès");
                // Clear fields
                firstNameField.clear();
                lastNameField.clear();
                emailField.clear();
                passwordField.clear();
                subjectField.clear();
            } else {
                feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #ff4466;");
                feedback.setText("❌ Erreur lors de l'ajout");
            }
        });

        // Delete action — uses email as identifier
        deleteBtn.setOnAction(e -> {
            String em = emailField.getText().trim();

            if (em.isEmpty()) {
                feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #ff4466;");
                feedback.setText("⚠️ Email requis pour supprimer");
                return;
            }

            boolean success = isProf
                    ? controller.deleteProfessor(em)
                    : controller.deleteStudent(em);

            if (success) {
                feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #00cc66;");
                feedback.setText("✅ Supprimé avec succès");
                emailField.clear();
            } else {
                feedback.setStyle("-fx-font-size: 12px; -fx-text-fill: #ff4466;");
                feedback.setText("❌ Erreur lors de la suppression");
            }
        });

        actionButtons.getChildren().addAll(addBtn, deleteBtn);
        panel.getChildren().add(actionButtons);

        return panel;
    }

    private void createBottomButtons() {
        HBox bottomButtons = new HBox(40);
        bottomButtons.setAlignment(Pos.CENTER);

        Button importBtn = new Button("Importer profil");
        Button exportBtn = new Button("Exporter PDF");

        importBtn.setStyle("-fx-font-size: 18px; -fx-padding: 15 45;");
        exportBtn.setStyle("-fx-font-size: 18px; -fx-padding: 15 45;");

        bottomButtons.getChildren().addAll(importBtn, exportBtn);
        this.getChildren().add(bottomButtons);
    }
}