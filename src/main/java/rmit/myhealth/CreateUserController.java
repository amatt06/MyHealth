package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import rmit.myhealth.model.MyHealth;
import rmit.myhealth.model.User;
import javafx.stage.Stage;

import java.io.File;

public class CreateUserController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button selectPictureButton;

    private final MyHealth myHealth;

    private LoginController loginController;

    private Image profilePicture;

    private boolean pictureAdded = false;

    public CreateUserController() {
        myHealth = MyHealth.getInstance();
    }

    // Setter method to set the LoginController instance
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @FXML
    protected boolean createUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        // Perform input field validation
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            feedbackLabel.setText("Please Complete All Fields");
            return false;
        }

        // Check if the username is already taken
        if (myHealth.getUserController().getUser(username) != null) {
            feedbackLabel.setText("Username unavailable");
            return false;
        }

        // Save the user in the backend
        myHealth.createUser(username, password, firstName, lastName);

        if (pictureAdded) {
            myHealth.getUserController().getUser(username).getProfile().setProfilePicture(profilePicture);
        }

        // Check if the user was stored successfully
        User storedUser = myHealth.getUserController().getUser(username);
        if (storedUser != null) {
            // Call the updateFeedbackLabel method in LoginController
            loginController.updateFeedbackLabel(true);
            // Close the window
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
            return true;
        }

        return false;
    }

    @FXML
    private void selectPicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Load the selected image file
            Image selectedPicture = new Image(selectedFile.toURI().toString());
            pictureAdded = true;
            profilePicture = selectedPicture;
        }
    }
}