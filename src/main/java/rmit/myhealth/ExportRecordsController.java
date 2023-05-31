package rmit.myhealth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    @FXML
    private Button exportButton;

    private ObservableList<HealthRecord> selectedRecords;

    // Initialise the table and retrieve selections.
    public void initialise() {
        selectedRecords = FXCollections.observableArrayList();
        RecordTable.setupTableColumns(recordsTable, weightColumn, temperatureColumn, bloodPressureUpperColumn, bloodPressureLowerColumn, noteColumn, dateTimeColumn);

        HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
        RecordTable.setRecordsTable(recordsTable, healthRecordController);

        recordsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        recordsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRecords.setAll(recordsTable.getSelectionModel().getSelectedItems());
        });

        // Apply CSS to highlight selected rows
        recordsTable.setRowFactory(tv -> {
            TableRow<HealthRecord> row = new TableRow<>();
            row.styleProperty().bind(
                    javafx.beans.binding.Bindings
                            .when(row.selectedProperty())
                            .then("-fx-background-color: lightblue;")
                            .otherwise(""));
            return row;
        });
    }

    // Pass the selected records and provide feedback.
    @FXML
    private void handleExport() {
        if (selectedRecords.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Records Selected", "Please select at least one record to export.");
        } else {
            if (exportRecords(selectedRecords)) {
                closeWindow();
                showAlert(Alert.AlertType.CONFIRMATION, "Export Confirmed", "Record was successfully exported.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Export Failed", "Record was not successfully exported.");
            }
        }
    }

    // Export the selected records to the csv.
    private boolean exportRecords(List<HealthRecord> records) {
        try {
            FileWriter fileWriter = new FileWriter("exported_records.csv");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Write CSV header
            printWriter.println("Weight,Temperature,BloodPressureUpper,BloodPressureLower,Note,DateTime");

            // Write each record to CSV
            for (HealthRecord record : records) {
                printWriter.printf("%.2f,%.2f,%.2f,%.2f,%s,%s%n",
                        record.getWeight(),
                        record.getTemperature(),
                        record.getBloodPressureUpper(),
                        record.getBloodPressureLower(),
                        record.getNote(),
                        record.getDateTime());
            }
            printWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}