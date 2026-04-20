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
        setupPanel();
        createHeader();
        createMainContent();
        createBottomButtons();
    }

    private void setupPanel() {
        this.getStyleClass().add("panel-admin");
        this.setPrefWidth(1150);
        this.setPrefHeight(650);
        this.setMaxWidth(1150);
        this.setSpacing(30);
        this.setPadding(new Insets(35));
        this.setAlignment(Pos.TOP_CENTER);
    }

    private void createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        VBox adminInfo = new VBox(6);
        adminInfo.setAlignment(Pos.CENTER_LEFT);

        Label adminTitle = new Label("ADMIN");
        adminTitle.getStyleClass().add("neon-title-large");
        adminTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 34));

        Label nameLabel = new Label("- NOM: MR");
        Label preLabel  = new Label("- PRENOM: HEY");
        nameLabel.getStyleClass().add("info-label-sub");
        preLabel.getStyleClass().add("info-label-sub");

        adminInfo.getChildren().addAll(adminTitle, nameLabel, preLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button menuButton = new Button("Menu");
        menuButton.getStyleClass().add("btn-admin-menu");
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

        VBox profsPanel = createManagementPanel("Profs:", true);

        // Center
        VBox centerArea = new VBox(30);
        centerArea.setAlignment(Pos.TOP_CENTER);

        Region topSpacer = new Region();
        topSpacer.setPrefHeight(40);

        Button searchButton = new Button("Recherche");
        searchButton.getStyleClass().add("btn-search");

        VBox resultsArea = new VBox(10);
        resultsArea.setPrefHeight(280);
        resultsArea.getStyleClass().add("results-area");

        centerArea.getChildren().addAll(topSpacer, searchButton, resultsArea);

        VBox studentsPanel = createManagementPanel("Eleves:", false);

        mainContent.getChildren().addAll(profsPanel, centerArea, studentsPanel);
        this.getChildren().add(mainContent);
    }

    private VBox createManagementPanel(String title, boolean isProf) {
        VBox panel = new VBox(12);
        panel.setPrefWidth(320);
        panel.getStyleClass().add("panel-dark");

        Label panelTitle = new Label(title);
        panelTitle.getStyleClass().add("neon-subtitle");
        panelTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 19));
        panel.getChildren().add(panelTitle);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("prénom");
        firstNameField.getStyleClass().add("field-dark");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("nom");
        lastNameField.getStyleClass().add("field-dark");

        TextField emailField = new TextField();
        emailField.setPromptText("mail");
        emailField.getStyleClass().add("field-dark");

        TextField passwordField = new TextField();
        passwordField.setPromptText("mot de passe");
        passwordField.getStyleClass().add("field-dark");

        TextField subjectField = new TextField();
        subjectField.setPromptText(isProf ? "matière enseignée" : "filière");
        subjectField.getStyleClass().add("field-dark");

        panel.getChildren().addAll(
                firstNameField, lastNameField,
                emailField, passwordField, subjectField
        );

        Label feedback = new Label();
        feedback.getStyleClass().add("feedback-neutral");
        panel.getChildren().add(feedback);

        HBox actionButtons = new HBox(15);
        actionButtons.setAlignment(Pos.CENTER);

        Button addBtn    = new Button("Ajouter");
        Button deleteBtn = new Button("Supprimer");
        addBtn.getStyleClass().add("btn-add");
        deleteBtn.getStyleClass().add("btn-delete");

        addBtn.setOnAction(e -> {
            String fn  = firstNameField.getText().trim();
            String ln  = lastNameField.getText().trim();
            String em  = emailField.getText().trim();
            String pw  = passwordField.getText().trim();
            String sub = subjectField.getText().trim();

            if (fn.isEmpty() || ln.isEmpty() || em.isEmpty() || pw.isEmpty() || sub.isEmpty()) {
                feedback.getStyleClass().setAll("feedback-error");
                feedback.setText("⚠️ Remplis tous les champs");
                return;
            }

            boolean success = isProf
                    ? controller.addProfessor(fn, ln, em, pw, sub)
                    : controller.addStudent(fn, ln, em, pw, sub);

            if (success) {
                feedback.getStyleClass().setAll("feedback-success");
                feedback.setText("✅ Ajouté avec succès");
                firstNameField.clear(); lastNameField.clear();
                emailField.clear(); passwordField.clear(); subjectField.clear();
            } else {
                feedback.getStyleClass().setAll("feedback-error");
                feedback.setText("❌ Erreur lors de l'ajout");
            }
        });

        deleteBtn.setOnAction(e -> {
            String em = emailField.getText().trim();
            if (em.isEmpty()) {
                feedback.getStyleClass().setAll("feedback-error");
                feedback.setText("⚠️ Email requis pour supprimer");
                return;
            }

            boolean success = isProf
                    ? controller.deleteProfessor(em)
                    : controller.deleteStudent(em);

            if (success) {
                feedback.getStyleClass().setAll("feedback-success");
                feedback.setText("✅ Supprimé avec succès");
                emailField.clear();
            } else {
                feedback.getStyleClass().setAll("feedback-error");
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
        importBtn.getStyleClass().add("btn-action");
        exportBtn.getStyleClass().add("btn-action");

        bottomButtons.getChildren().addAll(importBtn, exportBtn);
        this.getChildren().add(bottomButtons);
    }
}