package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rmit.myhealth.backend.MyHealth;
import rmit.myhealth.backend.User;

public class CreateUserView {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    private MyHealth myHealth;

    public CreateUserView() {
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
            System.out.println("User stored successfully: " + storedUser.getUsername());
        } else {
            System.out.println("Failed to store user");
        }

        // Clear input fields or show success message
        usernameField.clear();
        passwordField.clear();
        firstNameField.clear();
        lastNameField.clear();
    }
}
