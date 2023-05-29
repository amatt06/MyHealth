package rmit.myhealth;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

public class ViewRecordsController {
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
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        // Retrieve the user's health records from the HealthRecordController
        HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
        recordsTable.setItems(healthRecordController.getHealthRecords());
    }

}
