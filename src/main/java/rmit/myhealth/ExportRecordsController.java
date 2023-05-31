package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.util.Optional;

public class ExportRecordsController {
    @FXML
    private TableView<HealthRecord> recordsTable;

    @FXML
    private TableColumn<HealthRecord, Double> weightColumn;

    @FXML
    private TableColumn<HealthRecord, Double> temperatureColumn;

    @FXML
    private TableColumn<HealthRecord, Double> bloodPressureUpperColumn;

    @FXML
    private TableColumn<HealthRecord, Double> bloodPressureLowerColumn;

    @FXML
    private TableColumn<HealthRecord, String> noteColumn;

    @FXML
    private TableColumn<HealthRecord, String> dateTimeColumn;

    @FXML
    private Button cancelButton;

    // Present the table of records to the user.
    public void initialise() {
        RecordTable.setupTableColumns(recordsTable, weightColumn, temperatureColumn, bloodPressureUpperColumn, bloodPressureLowerColumn, noteColumn, dateTimeColumn);

        HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
        RecordTable.setRecordsTable(recordsTable, healthRecordController);

        // Add row click event handler for double click
        recordsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
                if (selectedRecord != null) {
                    exportRecords(selectedRecord);
                    closeWindow();
                    successAlert();

                }
            }
        });
    }

    private void exportRecords(HealthRecord record) {

    }


    // Close the current window
    @FXML
    private void closeWindow() {
        // Get the reference to the current window's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // Confirm successful export.
    private void successAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Record Exported");
        alert.setHeaderText(null);
        alert.setContentText("Record was successfully exported.");
        alert.showAndWait();
    }
}