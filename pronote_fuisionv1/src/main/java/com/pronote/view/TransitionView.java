package com.pronote.view;

import com.pronote.main.MainApp;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Objects;

public class TransitionView extends StackPane {

    public TransitionView(String role) {

        // Black background during load
        this.setStyle("-fx-background-color: black;");

        // Load video
        String videoPath = Objects.requireNonNull(
                getClass().getResource("/videos/door-open.mp4")
        ).toExternalForm();

        Media media = new Media(videoPath);
        MediaPlayer player = new MediaPlayer(media);
        MediaView mediaView = new MediaView(player);
        mediaView.setPreserveRatio(false);

        this.getChildren().add(mediaView);

        // Bind video size to scene (scene never changes so always correct)
        mediaView.fitWidthProperty().bind(MainApp.getScene().widthProperty());
        mediaView.fitHeightProperty().bind(MainApp.getScene().heightProperty());

        // Play when ready
        player.setOnReady(() -> Platform.runLater(player::play));

        // Navigate to panel when video ends
        player.setOnEndOfMedia(() -> Platform.runLater(() -> {
            player.dispose();
            navigateToPanel(role);
        }));

        // Skip video on error
        player.setOnError(() -> {
            System.err.println("Video error: " + player.getError().getMessage());
            Platform.runLater(() -> {
                player.dispose();
                navigateToPanel(role);
            });
        });
    }

    private void navigateToPanel(String role) {

        // Load background
        Image backgroundImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/space-background.jpg")
        ));

        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setSmooth(true);
        backgroundView.fitWidthProperty().bind(MainApp.getScene().widthProperty());
        backgroundView.fitHeightProperty().bind(MainApp.getScene().heightProperty());

        // Choose panel based on role
        Region panel = switch (role) {
            case "admin"   -> new AdminPanel();
            case "prof"    -> new ProfessorPanel();
            case "student" -> new StudentPanel();
            default        -> new AdminPanel();
        };

        AnchorPane dashboard = new AnchorPane();
        AnchorPane.setTopAnchor(panel, 100.0);
        AnchorPane.setLeftAnchor(panel, 400.0);
        AnchorPane.setRightAnchor(panel, 400.0);
        dashboard.getChildren().add(panel);

        StackPane dashRoot = new StackPane(backgroundView, dashboard);

        // Reload CSS
        MainApp.getScene().getStylesheets().clear();
        MainApp.getScene().getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/styles/neon-dashboard.css")
        ).toExternalForm());

        // Swap content
        MainApp.switchView(dashRoot, switch (role) {
            case "admin"   -> "EDUNAV-1 | ADMIN";
            case "prof"    -> "EDUNAV-1 | PROFESSOR";
            case "student" -> "EDUNAV-1 | STUDENT";
            default        -> "EDUNAV-1";
        });
    }
}