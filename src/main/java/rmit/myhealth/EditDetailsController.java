package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.MyHealth;

import java.time.LocalDateTime;

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

    // Save the new values of the record
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

            // Save the changes to the HealthRecordController or database

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }
}
