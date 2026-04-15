package com.plateformeproject.pronote.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        // Semi-transparent dark blue background with neon effect
        this.setStyle("-fx-background-color: rgba(8, 25, 48, 0.15); -fx-padding: 25; -fx-border-radius: 16;");
    }

    private void createHeader() {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER);

        // Left section - Name, Pre, Config
        HBox leftSection = new HBox(12);
        leftSection.setAlignment(Pos.CENTER_LEFT);

        Button nomBtn = new Button("NOM");
        Button preBtn = new Button("PRE");
        Button configBtn = new Button("config");

        leftSection.getChildren().addAll(nomBtn, preBtn, configBtn);

        // Center title
        Label title = new Label("Prof: Logiciel");
        title.setFont(Font.font("Orbitron", FontWeight.BOLD, 28));
        title.setStyle("-fx-text-fill: #00f7ff;");

        // Right section - Edit, STAT, Search
        HBox rightSection = new HBox(15);
        rightSection.setAlignment(Pos.CENTER_RIGHT);

        Button editBtn = new Button("Edit");
        Button statBtn = new Button("STAT");
        Button searchBtn = new Button("recherche");

        rightSection.getChildren().addAll(editBtn, statBtn, searchBtn);

        header.getChildren().addAll(leftSection, title, rightSection);
        this.getChildren().add(header);
    }

    private void createMainContent() {
        HBox mainContent = new HBox(30);

        // Left - Classes section
        VBox classesPanel = new VBox(15);
        classesPanel.setPrefWidth(300);

        Label classesTitle = new Label("CLASSES");
        classesTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));
        classesTitle.setStyle("-fx-text-fill: #00f7ff;");

        VBox classesList = new VBox(8);
        classesList.getChildren().addAll(
                createClassButton("CL1"),
                createClassButton("CL2"),
                createClassButton("CL3")
        );

        HBox classButtons = new HBox(15);
        classButtons.getChildren().addAll(
                new Button("Importer"),
                new Button("Exporter")
        );

        classesPanel.getChildren().addAll(classesTitle, classesList, classButtons);

        // Right - Students table
        VBox studentsPanel = new VBox(15);
        studentsPanel.setPrefWidth(700);

        Label studentsTitle = new Label("ÉLÈVES");
        studentsTitle.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));
        studentsTitle.setStyle("-fx-text-fill: #00f7ff;");

        TableView<Object> table = new TableView<>();
        table.setPrefHeight(340);

        TableColumn<Object, String> nameCol = new TableColumn<>("Noms et Prénoms");
        TableColumn<Object, String> ageCol = new TableColumn<>("Âge");
        TableColumn<Object, String> filiereCol = new TableColumn<>("Filière");
        TableColumn<Object, String> noteCol = new TableColumn<>("Note");

        table.getColumns().addAll(nameCol, ageCol, filiereCol, noteCol);

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
        Button btn = new Button("▶ " + className);
        btn.setPrefWidth(260);
        btn.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-text-fill: white; -fx-alignment: CENTER_LEFT;");
        return btn;
    }
}