package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.MyHealth;

import java.time.LocalDateTime;

public class NewRecordController {
    @FXML
    private TextField weightField;

    @FXML
    private TextField temperatureField;

    @FXML
    private TextField bloodPressureUpperField;

    @FXML
    private TextField bloodPressureLowerField;

    @FXML
    private TextArea noteField;

    @FXML
    private Button cancelButton;

    @FXML
    private void saveRecord() {
        if (MyHealth.getInstance().getCurrentUser() != null && MyHealth.getInstance().getCurrentUser().getHealthRecordController() != null) {
            // Retrieve the entered information from the form
            try {
                double weight = Double.parseDouble(weightField.getText());
                double temperature = Double.parseDouble(temperatureField.getText());
                int bloodPressureUpper = Integer.parseInt(bloodPressureUpperField.getText());
                int bloodPressureLower = Integer.parseInt(bloodPressureLowerField.getText());
                String note = noteField.getText();
                LocalDateTime dateTime = LocalDateTime.now();

                // Create a new HealthRecord object
                HealthRecord healthRecord = new HealthRecord(weight, temperature, bloodPressureUpper, bloodPressureLower, note, dateTime);

                // Add the record to the user's HealthRecordController
                MyHealth.getInstance().getCurrentUser().getHealthRecordController().addRecord(healthRecord);

                // Close the window
                closeWindow();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Values");
                alert.setHeaderText(null);
                alert.setContentText("Error.  Please enter valid values");
                alert.showAndWait();
            }
        }
    }


    @FXML
    private void closeWindow() {
        // Get the reference to the current window's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        // Close the window
        stage.close();
    }
}
