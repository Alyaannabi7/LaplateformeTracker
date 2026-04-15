package pronote.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pronote.view.MainView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        // Build the main login view
        MainView root = new MainView();

        // Set scene size to match the space corridor aesthetic
        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Pronote - Secure Access");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
