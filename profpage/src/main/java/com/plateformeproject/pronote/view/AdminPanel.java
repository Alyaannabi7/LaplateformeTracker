package com.plateformeproject.pronote.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminPanel extends VBox {

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

        // Fond bleu très transparent (comme tu veux)
        this.setStyle("-fx-background-color: rgba(8, 25, 48, 0.15); " +   // 0.45 = assez transparent
                "-fx-border-color: #00f7ff; " +
                "-fx-border-width: 3px; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-effect: dropshadow(gaussian, #00f7ff, 35, 0.75, 0, 0);");
    }

    private void createHeader() {
        // Main header container
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));   // space below header

        // Admin Information (left side)
        VBox adminInfo = new VBox(6);
        adminInfo.setAlignment(Pos.CENTER_LEFT);

        Label adminTitle = new Label("ADMIN");
        adminTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 34));
        adminTitle.setStyle("-fx-text-fill: green; -fx-effect: dropshadow(gaussian, #00f7ff, 25, 0.9, 0, 0);");

        Label nameLabel = new Label("- NOM: MR");
        Label preLabel = new Label("- PRE HEY");

        nameLabel.setStyle("-fx-text-fill: #b0f8ff; -fx-font-size: 17px;");
        preLabel.setStyle("-fx-text-fill: #b0f8ff; -fx-font-size: 17px;");

        adminInfo.getChildren().addAll(adminTitle, nameLabel, preLabel);

        // Flexible space to push the button to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Menu Button (right side)
        Button menuButton = new Button("Menu");
        menuButton.setStyle("-fx-font-size: 18px; " +
                "-fx-padding: 12 40; " +
                "-fx-background-color:blue; " +
                "-fx-border-color: #00f7ff; " +
                "-fx-border-width: 2;");

        // Add elements: Admin Info → Spacer → Menu Button
        header.getChildren().addAll(adminInfo, spacer, menuButton);

        this.getChildren().add(header);
    }

    private void createMainContent() {
        HBox mainContent = new HBox(50);
        mainContent.setAlignment(Pos.CENTER);

        VBox profsPanel = createManagementPanel("Profs:",
                new String[]{"nom", "prénom", "mail", "mot de passe", "matière enseignée"});

        // ==================== ZONE CENTRALE ====================
        VBox centerArea = new VBox(30);
        centerArea.setAlignment(Pos.TOP_CENTER);

        // Espace vide en haut pour remonter le bouton
        Region topSpacer = new Region();
        topSpacer.setPrefHeight(40);        // ← Change cette valeur pour monter/descendre le bouton

        Button searchButton = new Button("Recherche");
        searchButton.setStyle("-fx-font-size: 22px; -fx-padding: 15 80;");

        VBox resultsArea = new VBox(10);
        resultsArea.setPrefHeight(280);
        resultsArea.setStyle("-fx-background-color: rgba(255,255,255,0.03); " +
                "-fx-border-color: #00f7ff; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 12;");

        centerArea.getChildren().addAll(topSpacer, searchButton, resultsArea);
        // =======================================================

        VBox studentsPanel = createManagementPanel("Eleves:",
                new String[]{"nom", "prénom", "mail", "mot de passe", "matière enseignée"});

        mainContent.getChildren().addAll(profsPanel, centerArea, studentsPanel);
        this.getChildren().add(mainContent);
    }
    private VBox createManagementPanel(String title, String[] fields) {
        VBox panel = new VBox(12);
        panel.setPrefWidth(320);
        panel.setStyle("-fx-background-color: rgba(8, 25, 48, 0.92); -fx-padding: 25; -fx-border-radius: 16;");

        Label panelTitle = new Label(title);
        panelTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 19));
        panelTitle.setStyle("-fx-text-fill: #00f7ff;");

        panel.getChildren().add(panelTitle);

        for (String field : fields) {
            TextField tf = new TextField();
            tf.setPromptText(field);
            tf.setStyle("-fx-background-color: rgba(255,255,255,0.07); -fx-text-fill: white; -fx-prompt-text-fill: #777777;");
            panel.getChildren().add(tf);
        }

        HBox actionButtons = new HBox(15);
        actionButtons.setAlignment(Pos.CENTER);

        Button addBtn = new Button("ajouter");
        Button deleteBtn = new Button("supprimer");

        addBtn.setStyle("-fx-background-color: #00cc66; -fx-padding: 8 20;");
        deleteBtn.setStyle("-fx-background-color: #ff3366; -fx-padding: 8 20;");

        actionButtons.getChildren().addAll(addBtn, deleteBtn);
        panel.getChildren().add(actionButtons);

        return panel;
    }

    private void createBottomButtons() {
        HBox bottomButtons = new HBox(40);
        bottomButtons.setAlignment(Pos.CENTER);

        Button importBtn = new Button("importer profil");
        Button exportBtn = new Button("exporté pdf");

        importBtn.setStyle("-fx-font-size: 18px; -fx-padding: 15 45;");
        exportBtn.setStyle("-fx-font-size: 18px; -fx-padding: 15 45;");

        bottomButtons.getChildren().addAll(importBtn, exportBtn);
        this.getChildren().add(bottomButtons);
    }
}