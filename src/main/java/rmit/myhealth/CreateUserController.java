package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rmit.myhealth.model.MyHealth;
import rmit.myhealth.model.User;
import javafx.stage.Stage;

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

    private final MyHealth myHealth;

    public CreateUserController() {
        myHealth = MyHealth.getInstance();
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
            feedbackLabel.setStyle("-fx-text-fill: red;");

            clearFields();

            return false;
        }

        // Save the user in the backend
        myHealth.createUser(username, password, firstName, lastName);

        // Check if the user was stored successfully
        User storedUser = myHealth.getUserController().getUser(username);
        if (storedUser != null) {
            // Close the window
            Stage stage = (Stage) feedbackLabel.getScene().getWindow();
            stage.close();

            return true;
        }
        return false;
    }

    private void clearFields() {
        // Clear input fields
        usernameField.clear();
        passwordField.clear();
        firstNameField.clear();
        lastNameField.clear();
    }
}
