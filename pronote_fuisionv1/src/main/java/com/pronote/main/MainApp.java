package com.pronote.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.pronote.view.MainView;
import java.util.Objects;

public class MainApp extends Application {

    private static StackPane root;
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        System.out.println("Encoding: " + java.nio.charset.Charset.defaultCharset());
        System.out.println("file.encoding: " + System.getProperty("file.encoding"));
        primaryStage = stage;

        root = new StackPane();
        scene = new Scene(root);
        scene.getStylesheets().add(
                Objects.requireNonNull(
                        getClass().getResource("/styles/neon-dashboard.css")
                ).toExternalForm());

        // Start with login view
        root.getChildren().add(new MainView());

        stage.setTitle("EDUNAV-1 - Secure Access");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    // Swap content without changing scene or stage size
    public static void switchView(javafx.scene.Node newView, String title) {
        root.getChildren().clear();
        root.getChildren().add(newView);
        primaryStage.setTitle(title);
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}