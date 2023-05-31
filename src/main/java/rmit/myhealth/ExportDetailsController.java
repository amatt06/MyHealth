package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;

public class ExportDetailsController {
    @FXML
    private Button exportButton;

    @FXML
    private Button cancelButton;

    private HealthRecord record;


    @FXML
    private void exportRecords() {

    }

    @FXML
    private void closeWindow() {
        // Get the reference to the current window's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void successAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Record Exported");
        alert.setHeaderText(null);
        alert.setContentText("Record was successfully exported.");
        alert.showAndWait();
    }
}