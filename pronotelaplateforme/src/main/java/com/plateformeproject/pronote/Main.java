package com.plateformeproject.pronote;

import com.plateformeproject.pronote.view.AdminPanel;
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

        // Load the space background image
        Image backgroundImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/space-background.jpg")));

        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setSmooth(true);

        // Main dashboard container
        AnchorPane dashboard = new AnchorPane();

        // Create Admin Panel
        AdminPanel adminPanel = new AdminPanel();

        // Center the admin panel
        AnchorPane.setTopAnchor(adminPanel, 100.0);
        AnchorPane.setLeftAnchor(adminPanel, 400.0);
        AnchorPane.setRightAnchor(adminPanel, 400.0);

        dashboard.getChildren().add(adminPanel);

        // Root layout
        StackPane root = new StackPane(backgroundView, dashboard);

        Scene scene = new Scene(root);

        // Load CSS styles
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/styles/neon-dashboard.css")).toExternalForm());

        primaryStage.setTitle("Pronote / ENT - EDUNAV-1 | ADMIN");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Make background fill the entire window
        backgroundView.fitWidthProperty().bind(scene.widthProperty());
        backgroundView.fitHeightProperty().bind(scene.heightProperty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}