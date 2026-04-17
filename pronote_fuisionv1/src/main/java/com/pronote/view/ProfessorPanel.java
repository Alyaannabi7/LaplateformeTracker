package com.pronote.view;

import com.pronote.main.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class ProfessorPanel extends VBox {

    public ProfessorPanel() {
        setStyle();
        createHeader();
        createMainContent();
    }

    private void setStyle() {
        this.getStyleClass().add("panel");
        this.setPrefWidth(1180);
        this.setPrefHeight(680);
        this.setMaxWidth(1180);
        this.setSpacing(25);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle(
                "-fx-background-color: rgba(8, 25, 48, 0.15); " +
                        "-fx-padding: 25; " +
                        "-fx-border-radius: 16;"
        );
    }

    private void createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        // Left - NOM and PRENOM as info labels
        VBox leftSection = new VBox(4);
        leftSection.setAlignment(Pos.CENTER_LEFT);

        Label nomLabel = new Label("Prénom: Lénnie");
        Label prenomLabel = new Label("Nom: Barr");
        nomLabel.setFont(Font.font("Orbitron", FontWeight.BOLD, 14));
        prenomLabel.setFont(Font.font("Orbitron", FontWeight.BOLD, 14));
        nomLabel.setStyle("-fx-text-fill: #ffffff;");
        prenomLabel.setStyle("-fx-text-fill: #ffffff;");

        leftSection.getChildren().addAll(nomLabel, prenomLabel);

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);

        // Center title — no glow
        Label title = new Label("Prof: Logiciel");
        title.setFont(Font.font("Orbitron", FontWeight.BOLD, 28));
        title.setStyle("-fx-text-fill: #00f7ff;");

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // Right section
        HBox rightSection = new HBox(12);
        rightSection.setAlignment(Pos.CENTER_RIGHT);

        Button editBtn = new Button("Edit");
        Button statBtn = new Button("STAT");

        // Search field
        TextField searchField = new TextField();
        searchField.setPromptText("Recherche...");
        searchField.setPrefWidth(160);
        searchField.setStyle(
                "-fx-background-color: rgba(0, 20, 40, 0.85); " +
                        "-fx-text-fill: #00f7ff; " +
                        "-fx-prompt-text-fill: #336677; " +
                        "-fx-border-color: #00f7ff; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 4; " +
                        "-fx-background-radius: 4; " +
                        "-fx-font-size: 13px; " +
                        "-fx-padding: 6 10;"
        );

        // Menu button — amber orange complement color
        Button menuBtn = new Button("← MENU");
        menuBtn.setFont(Font.font("Orbitron", FontWeight.BOLD, 13));
        menuBtn.setStyle(
                "-fx-background-color: rgba(255, 107, 0, 0.25); " +
                        "-fx-text-fill: #FF6B00; " +
                        "-fx-border-color: #FF6B00; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-padding: 10 24; " +
                        "-fx-cursor: hand;"
        );
        menuBtn.setOnMouseEntered(e -> menuBtn.setStyle(
                "-fx-background-color: rgba(255, 107, 0, 0.45); " +
                        "-fx-text-fill: #ffffff; " +
                        "-fx-border-color: #FF6B00; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-padding: 10 24; " +
                        "-fx-cursor: hand;"
        ));
        menuBtn.setOnMouseExited(e -> menuBtn.setStyle(
                "-fx-background-color: rgba(255, 107, 0, 0.25); " +
                        "-fx-text-fill: #FF6B00; " +
                        "-fx-border-color: #FF6B00; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-padding: 10 24; " +
                        "-fx-cursor: hand;"
        ));
        menuBtn.setOnAction(e -> javafx.application.Platform.runLater(() -> {
            MainApp.getScene().getStylesheets().clear();
            MainApp.getScene().getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/styles/neon-dashboard.css")
                    ).toExternalForm()
            );
            MainApp.switchView(new MainView(), "EDUNAV-1 - Secure Access");
        }));

        rightSection.getChildren().addAll(editBtn, statBtn, searchField, menuBtn);
        header.getChildren().addAll(leftSection, leftSpacer, title, rightSpacer, rightSection);
        this.getChildren().add(header);
    }

    private void createMainContent() {
        HBox mainContent = new HBox(30);

        // Left - class list
        VBox classesPanel = new VBox(15);
        classesPanel.setPrefWidth(280);

        // Année title — no glow
        Label classesTitle = new Label("Année");
        classesTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));
        classesTitle.setStyle("-fx-text-fill: #00f7ff;");

        VBox classesList = new VBox(8);
        classesList.getChildren().addAll(
                createClassButton("LV 1"),
                createClassButton("LV 2"),
                createClassButton("LV 3")
        );

        HBox classButtons = new HBox(15);
        classButtons.getChildren().addAll(
                new Button("Importer"),
                new Button("Exporter")
        );

        classesPanel.getChildren().addAll(classesTitle, classesList, classButtons);

        // Right - students table
        VBox studentsPanel = new VBox(15);
        studentsPanel.setPrefWidth(860);

        // ÉLÈVES title — no glow
        Label studentsTitle = new Label("ÉLÈVES");
        studentsTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));
        studentsTitle.setStyle("-fx-text-fill: #00f7ff;");

        TableView<Object> table = new TableView<>();
        table.setPrefHeight(340);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Object, String> nomCol     = new TableColumn<>("Nom");
        TableColumn<Object, String> prenomCol  = new TableColumn<>("Prénom");
        TableColumn<Object, String> ageCol     = new TableColumn<>("Âge");
        TableColumn<Object, String> filiereCol = new TableColumn<>("Filière");
        TableColumn<Object, String> noteCol    = new TableColumn<>("Note");

        nomCol.setMinWidth(120);
        prenomCol.setMinWidth(120);
        ageCol.setMinWidth(60);
        filiereCol.setMinWidth(100);
        noteCol.setMinWidth(80);

        table.getColumns().addAll(nomCol, prenomCol, ageCol, filiereCol, noteCol);

        HBox studentButtons = new HBox(20);
        studentButtons.getChildren().addAll(
                new Button("Importer"),
                new Button("Exporter")
        );

        studentsPanel.getChildren().addAll(studentsTitle, table, studentButtons);

        mainContent.getChildren().addAll(classesPanel, studentsPanel);
        this.getChildren().add(mainContent);
    }

    private Button createClassButton(String className) {
        Button btn = new Button("▶  " + className);
        btn.setPrefWidth(240);
        btn.setFont(Font.font("Orbitron", FontWeight.BOLD, 13));

        // Darker blue style
        String normal =
                "-fx-background-color: rgba(0, 80, 160, 0.6); " +
                        "-fx-text-fill: #a8d8ff; " +
                        "-fx-border-color: #0088cc; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-alignment: CENTER_LEFT; " +
                        "-fx-padding: 10 15; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow(gaussian, #0088cc, 9, 0.3, 0, 0);";

        String hovered =
                "-fx-background-color: rgba(0, 100, 200, 0.75); " +
                        "-fx-text-fill: #ffffff; " +
                        "-fx-border-color: #0088cc; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-alignment: CENTER_LEFT; " +
                        "-fx-padding: 10 15; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow(gaussian, #0088cc, 9, 0.3, 0, 0);";

        btn.setStyle(normal);
        btn.setOnMouseEntered(e -> btn.setStyle(hovered));
        btn.setOnMouseExited(e -> btn.setStyle(normal));

        return btn;
    }
}