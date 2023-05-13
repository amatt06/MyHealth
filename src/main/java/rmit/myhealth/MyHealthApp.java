package rmit.myhealth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyHealthApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyHealthApp.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 680);
        stage.setTitle("Welcome to MyHealth");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}