package com.pronote.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.pronote.view.MainView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        // Build the login view
        MainView root = new MainView();

        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("EDUNAV-1 - Secure Access");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}