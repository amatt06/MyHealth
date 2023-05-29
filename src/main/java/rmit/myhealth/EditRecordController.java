package rmit.myhealth;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditRecordController {
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
    void setRecordsTable(ObservableList<HealthRecord> healthRecords) {
        // Set up the table columns
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        bloodPressureUpperColumn.setCellValueFactory(new PropertyValueFactory<>("bloodPressureUpper"));
        bloodPressureLowerColumn.setCellValueFactory(new PropertyValueFactory<>("bloodPressureLower"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        dateTimeColumn.setCellValueFactory(cellData -> {
            LocalDateTime dateTime = cellData.getValue().getDateTime();
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy (H:m)"));
            return new SimpleStringProperty(formattedDateTime);
        });

        // Retrieve the user's health records from the HealthRecordController
        HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
        recordsTable.setItems(healthRecordController.getHealthRecords());
    }

}
