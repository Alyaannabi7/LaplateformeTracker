package com.pronote.view;

import com.pronote.controller.ProfessorController;
import com.pronote.main.MainApp;
import com.pronote.model.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class ProfessorPanel extends VBox {

    private final TableView<Student> table = new TableView<>();
    private final ProfessorController controller = new ProfessorController();
    private final Button editBtn = new Button("Edit");
    private final Button statBtn = new Button("STAT");

    public ProfessorPanel() {
        setupPanel();
        createHeader();
        createMainContent();
    }

    private void setupPanel() {
        this.getStyleClass().add("panel-prof");
        this.setPrefWidth(1180);
        this.setPrefHeight(680);
        this.setMaxWidth(1180);
        this.setSpacing(25);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.TOP_CENTER);
    }

    private void createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        // Left — info labels
        VBox leftSection = new VBox(4);
        leftSection.setAlignment(Pos.CENTER_LEFT);

        Label nomLabel    = new Label("Prénom: Lénnie");
        Label prenomLabel = new Label("Nom: Barr");
        nomLabel.getStyleClass().add("info-label");
        prenomLabel.getStyleClass().add("info-label");

        leftSection.getChildren().addAll(nomLabel, prenomLabel);

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);

        // Center title
        Label title = new Label("Prof: Logiciel");
        title.getStyleClass().add("neon-title");
        title.setFont(Font.font("Orbitron", FontWeight.BOLD, 28));

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // Right section — search + menu only
        HBox rightSection = new HBox(12);
        rightSection.setAlignment(Pos.CENTER_RIGHT);

        TextField searchField = new TextField();
        searchField.setPromptText("Recherche...");
        searchField.getStyleClass().add("field-search");

        Button menuBtn = new Button("MENU");
        menuBtn.getStyleClass().add("btn-menu");
        menuBtn.setFont(Font.font("Orbitron", FontWeight.BOLD, 13));
        menuBtn.setOnAction(e -> javafx.application.Platform.runLater(() -> {
            MainApp.getScene().getStylesheets().clear();
            MainApp.getScene().getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/styles/neon-dashboard.css")
                    ).toExternalForm()
            );
            MainApp.switchView(new MainView(), "EDUNAV-1 - Secure Access");
        }));

        rightSection.getChildren().addAll(searchField, menuBtn);
        header.getChildren().addAll(leftSection, leftSpacer, title, rightSpacer, rightSection);
        this.getChildren().add(header);
    }

    private void createMainContent() {
        HBox mainContent = new HBox(30);

        // Left — class list
        VBox classesPanel = new VBox(15);
        classesPanel.setPrefWidth(280);

        Label classesTitle = new Label("Année");
        classesTitle.getStyleClass().add("neon-subtitle");
        classesTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));

        Button lv1Btn = createClassButton("LV 1");
        Button lv2Btn = createClassButton("LV 2");
        Button lv3Btn = createClassButton("LV 3");

        lv1Btn.setOnAction(e -> loadStudents(1));
        lv2Btn.setOnAction(e -> loadStudents(2));
        lv3Btn.setOnAction(e -> loadStudents(3));

        VBox classesList = new VBox(8, lv1Btn, lv2Btn, lv3Btn);



        classesPanel.getChildren().addAll(classesTitle, classesList);

        // Right — students table
        VBox studentsPanel = new VBox(15);
        studentsPanel.setPrefWidth(860);

        // ÉLÈVES title with Edit and STAT buttons
        Label studentsTitle = new Label("ÉLÈVES");
        studentsTitle.getStyleClass().add("neon-subtitle");
        studentsTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));

        editBtn.getStyleClass().add("btn-cyan");
        statBtn.getStyleClass().add("btn-cyan");

        HBox studentsTitleRow = new HBox(15, studentsTitle, editBtn, statBtn);
        studentsTitleRow.setAlignment(Pos.CENTER_LEFT);

        table.setPrefHeight(340);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Student, String> nomCol     = new TableColumn<>("Nom");
        TableColumn<Student, String> prenomCol  = new TableColumn<>("Prénom");
        TableColumn<Student, String> ageCol     = new TableColumn<>("Âge");
        TableColumn<Student, String> filiereCol = new TableColumn<>("Filière");
        TableColumn<Student, String> noteCol    = new TableColumn<>("Note");

        nomCol.setCellValueFactory(d     -> new SimpleStringProperty(d.getValue().lastName));
        prenomCol.setCellValueFactory(d  -> new SimpleStringProperty(d.getValue().firstName));
        ageCol.setCellValueFactory(d     -> new SimpleStringProperty(d.getValue().age));
        filiereCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().filiere));
        noteCol.setCellValueFactory(d    -> new SimpleStringProperty(d.getValue().grade));

        nomCol.setMinWidth(120);
        prenomCol.setMinWidth(120);
        ageCol.setMinWidth(60);
        filiereCol.setMinWidth(100);
        noteCol.setMinWidth(80);

        table.getColumns().addAll(nomCol, prenomCol, ageCol, filiereCol, noteCol);

        HBox studentButtons = new HBox(20);
        Button importBtn = new Button("Importer");
        Button exportBtn = new Button("Exporter");
        importBtn.getStyleClass().add("btn-cyan");
        exportBtn.getStyleClass().add("btn-cyan");
        studentButtons.getChildren().addAll(importBtn, exportBtn);

        studentsPanel.getChildren().addAll(studentsTitleRow, table, studentButtons);

        mainContent.getChildren().addAll(classesPanel, studentsPanel);
        this.getChildren().add(mainContent);
    }

    private void loadStudents(int level) {
        table.setItems(controller.getStudentsByLevel(level));
    }

    private Button createClassButton(String className) {
        Button btn = new Button("▶  " + className);
        btn.getStyleClass().add("btn-lv");
        btn.setFont(Font.font("Orbitron", FontWeight.BOLD, 13));
        return btn;
    }
}