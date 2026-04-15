package com.plateformeproject.pronote;

import com.plateformeproject.pronote.view.ProfessorPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Load space background image
        Image backgroundImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/space-background.jpg")));

        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setSmooth(true);

        // Main dashboard container
        AnchorPane dashboard = new AnchorPane();

        // Professor Panel
        ProfessorPanel professorPanel = new ProfessorPanel();

        // Center the panel
        AnchorPane.setTopAnchor(professorPanel, 250.0);
        AnchorPane.setLeftAnchor(professorPanel, 450.0);
        AnchorPane.setRightAnchor(professorPanel, 450.0);

        dashboard.getChildren().add(professorPanel);

        StackPane root = new StackPane(backgroundView, dashboard);

        Scene scene = new Scene(root);

        // Load CSS
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/styles/neon-dashboard.css")).toExternalForm());

        primaryStage.setTitle("Pronote / ENT - EDUNAV-1 | Professor");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Background fills the window
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}