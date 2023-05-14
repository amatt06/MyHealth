package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rmit.myhealth.backend.MyHealth;
import rmit.myhealth.backend.User;

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
    protected void createUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        // Perform validation if necessary

        // Save the user in the backend
        myHealth.createUser(username, password, firstName, lastName);

        // Check if the user was stored successfully
        User storedUser = myHealth.getUserController().getUser(username);
        if (storedUser != null) {
            feedbackLabel.setText("User: " + storedUser.getUsername() + " Successfully Created");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            feedbackLabel.setText("Failed To Create User");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }

        // Clear input fields
        usernameField.clear();
        passwordField.clear();
        firstNameField.clear();
        lastNameField.clear();
    }
}
