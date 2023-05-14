package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button createUserButton;

    @FXML
    protected void initialize() {
        titleLabel.setText("MyHealth");
    }

    @FXML
    protected void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform login logic here
    }

    @FXML
    private void createUser() throws IOException {
        // Load the Create User FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
        Parent createUserRoot = fxmlLoader.load();
        CreateUserController createUserController = fxmlLoader.getController();

        // Create a new stage for the Create User page
        Stage createUserStage = new Stage();
        createUserStage.setTitle("Create User");
        createUserStage.setScene(new Scene(createUserRoot));

        // Show the Create User page
        createUserStage.show();
    }
}
