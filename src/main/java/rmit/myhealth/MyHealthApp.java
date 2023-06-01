package rmit.myhealth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rmit.myhealth.model.MyHealth;

import java.io.IOException;
import java.sql.Connection;

public class MyHealthApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MyHealth myHealth = MyHealth.getInstance();

        // Connect to the database
        Connection connection = myHealth.connectToDatabase();

        // Create the necessary tables
        myHealth.createTables(connection);

        // Open the login screen
        openLogin();
    }


    public static void openLogin() throws IOException {
        MyHealth myHealth = MyHealth.getInstance();


        FXMLLoader fxmlLoader = new FXMLLoader(MyHealthApp.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 680);
        Stage stage = new Stage();
        stage.setTitle("Welcome to MyHealth");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}