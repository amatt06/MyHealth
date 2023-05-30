package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rmit.myhealth.model.MyHealth;
import rmit.myhealth.model.User;

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
    protected void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean loginStatus = myHealth.login(username, password);

        if (loginStatus) {
            // Load and display the User board window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserBoard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Pass the user's name and last name to the User Dashboard controller
            UserBoardController userBoardController = fxmlLoader.getController();
            User loggedInUser = myHealth.getUserController().getUser(username);
            userBoardController.setUserDetails(loggedInUser.getProfile().getFirstName(), loggedInUser.getProfile().getLastName());
            userBoardController.setProfilePicture(loggedInUser.getProfile().getProfilePicture());

            Stage stage = new Stage();
            stage.setTitle("User Dashboard");
            stage.setScene(scene);
            stage.show();

            // Close the login window
            Stage loginStage = (Stage) loginButton.getScene().getWindow();
            loginStage.close();
        } else {
            // Login failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
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

            // Pass the LoginController instance to the CreateUserController
            CreateUserController createUserController = fxmlLoader.getController();
            createUserController.setLoginController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method called by CreateUserController to update the feedback label
    public void updateFeedbackLabel(boolean creationStatus) {
        if (creationStatus) {
            createUserFeedbackLabel.setText("User creation successful");
            createUserFeedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            createUserFeedbackLabel.setText("User creation failed");
            createUserFeedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
