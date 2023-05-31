package rmit.myhealth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.io.File;
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

    private String filePath;

    private String fileName;

    // Receive the file path from export details.
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Receive the file name from export details.
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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
    public void handleExport() {
        if (selectedRecords.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Records Selected", "Please select at least one record to export.");
        } else if (filePath == null) {
            showAlert(Alert.AlertType.WARNING, "Missing File Path", "Please choose a file location.");
        } else if (fileName == null) {
            showAlert(Alert.AlertType.WARNING, "Missing File Name", "Please choose a file name.");
        } else {
            File directory = new File(filePath);
            if (!directory.exists()) {
                showAlert(Alert.AlertType.ERROR, "Directory Not Found", "The specified directory does not exist.");
                return;
            }

            File file = new File(directory, fileName);
            if (exportRecords(selectedRecords, file)) {
                closeWindow();
                showAlert(Alert.AlertType.CONFIRMATION, "Export Confirmed", "Records were successfully exported.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Export Failed", "Records were not successfully exported.");
            }
        }
    }


    // Export the selected records to the csv.
    private boolean exportRecords(List<HealthRecord> records, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
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
    private void openExportDetailsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ExportDetails.fxml"));
            Parent root = loader.load();
            ExportDetailsController controller = loader.getController();
            controller.setExportRecordsController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void closeWindow() {
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