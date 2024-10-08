package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;

public class EditDetailsController {
    @FXML
    private TextField weightTextField;

    @FXML
    private TextField temperatureTextField;

    @FXML
    private TextField bloodPressureUpperTextField;

    @FXML
    private TextField bloodPressureLowerTextField;

    @FXML
    private TextArea noteTextArea;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private HealthRecord record;

    public void setRecord(HealthRecord record) {
        this.record = record;
        displayRecordDetails();
    }

    // Pre-fill the fields
    private void displayRecordDetails() {
        weightTextField.setText(Double.toString(record.getWeight()));
        temperatureTextField.setText(Double.toString(record.getTemperature()));
        bloodPressureUpperTextField.setText(Double.toString(record.getBloodPressureUpper()));
        bloodPressureLowerTextField.setText(Double.toString(record.getBloodPressureLower()));
        noteTextArea.setText(record.getNote());
    }

    // Retrieve and Save edited record.
    @FXML
    private void saveRecord() {
        if (record != null) {
            double newWeight = Double.parseDouble(weightTextField.getText());
            double newTemperature = Double.parseDouble(temperatureTextField.getText());
            double newBloodPressureUpper = Double.parseDouble(bloodPressureUpperTextField.getText());
            double newBloodPressureLower = Double.parseDouble(bloodPressureLowerTextField.getText());
            String newNote = noteTextArea.getText();

            record.setWeight(newWeight);
            record.setTemperature(newTemperature);
            record.setBloodPressureUpper(newBloodPressureUpper);
            record.setBloodPressureLower(newBloodPressureLower);
            record.setNote(newNote);

            closeWindow();
            successAlert();
        }
    }

    @FXML
    private void closeWindow() {
        // Get the reference to the current window's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void successAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Record Updated");
        alert.setHeaderText(null);
        alert.setContentText("Record was successfully updated.");
        alert.showAndWait();
    }
}