package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rmit.myhealth.backend.UserController;

public class CreateUserController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    private UserController userController;

    public CreateUserController() {
        userController = new UserController();
    }

    @FXML
    protected void createUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        // Perform validation on the input fields

        // Create a new user using the UserController
        userController.createUser(username, password, firstName, lastName);


        // Clear the input fields
        usernameField.clear();
        passwordField.clear();
        firstNameField.clear();
        lastNameField.clear();
    }
}
