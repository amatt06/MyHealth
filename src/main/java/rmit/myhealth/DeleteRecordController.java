package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.util.Optional;

public class DeleteRecordController {
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

    public void initialise() {
        RecordTable.setupTableColumns(recordsTable, weightColumn, temperatureColumn, bloodPressureUpperColumn, bloodPressureLowerColumn, noteColumn, dateTimeColumn);

        HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
        RecordTable.setRecordsTable(recordsTable, healthRecordController);

        // Add row click event handler for double click
        recordsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
                if (selectedRecord != null) {
                    if (deleteRecord(selectedRecord)) {
                        closeWindow();
                        successAlert();
                    }
                }
            }
        });
    }

    private boolean deleteRecord(HealthRecord record) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete this record?");

        ButtonType deleteButtonType = new ButtonType("Delete");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirmAlert.getButtonTypes().setAll(deleteButtonType, cancelButtonType);

        boolean confirmed = false;

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == deleteButtonType) {
            HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
            healthRecordController.deleteRecord(record);
            confirmed = true;
        }
        return confirmed;
    }


    @FXML
    private void closeWindow() {
        // Get the reference to the current window's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void successAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Record Deleted");
        alert.setHeaderText(null);
        alert.setContentText("Record was successfully deleted.");
        alert.showAndWait();
    }
}