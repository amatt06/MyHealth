package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserBoardController {
    @FXML
    private Label welcomeLabel;

    public void setUserDetails(String firstName, String lastName) {
        welcomeLabel.setText(firstName + " " + lastName);
    }
}
