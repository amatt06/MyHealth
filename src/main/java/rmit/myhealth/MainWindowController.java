package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    protected void createUser() {
        // Show the create user form or navigate to the create user page
    }
}
