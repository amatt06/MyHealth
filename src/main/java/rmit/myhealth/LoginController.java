package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rmit.myhealth.backend.MyHealth;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final MyHealth myHealth;

    @FXML
    private Button loginButton;

    @FXML
    private Button createUserButton;

    @FXML
    private Label createUserFeedbackLabel;

    public LoginController() {
        myHealth = MyHealth.getInstance();
    }

    @FXML
    protected void initialize() {
        titleLabel.setText("MyHealth");
    }

    @FXML
    protected void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean loginStatus = myHealth.login(username, password);

        Alert alert;
        if (loginStatus) {
            // Login successful
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Logged in successfully!");

        } else {
            // Login failed
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
        }
        alert.showAndWait();
    }


    @FXML
    protected void createUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Create User");
            stage.setScene(scene);

            // Provide feedback on entered user details.
            stage.setOnHidden(event -> {
                CreateUserController createUserController = fxmlLoader.getController();
                boolean creationStatus = createUserController.createUser();

                if (creationStatus) {
                    createUserFeedbackLabel.setText("User creation successful");
                    createUserFeedbackLabel.setStyle("-fx-text-fill: green;");
                } else {
                    createUserFeedbackLabel.setText("User creation failed");
                    createUserFeedbackLabel.setStyle("-fx-text-fill: red;");
                }
            });

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

