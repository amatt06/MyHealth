package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;

public class ExportDetailsController {

    @FXML
    private Button cancelButton;

    private HealthRecord record;

    @FXML
    private void closeWindow() {
        // Get the reference to the current window's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}