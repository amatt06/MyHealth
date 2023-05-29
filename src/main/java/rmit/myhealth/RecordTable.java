package rmit.myhealth;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordTable {
    public static void setupTableColumns(TableView<HealthRecord> recordsTable,
                                         TableColumn<HealthRecord, Double> weightColumn,
                                         TableColumn<HealthRecord, Double> temperatureColumn,
                                         TableColumn<HealthRecord, Double> bloodPressureUpperColumn,
                                         TableColumn<HealthRecord, Double> bloodPressureLowerColumn,
                                         TableColumn<HealthRecord, String> noteColumn,
                                         TableColumn<HealthRecord, String> dateTimeColumn) {
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        bloodPressureUpperColumn.setCellValueFactory(new PropertyValueFactory<>("bloodPressureUpper"));
        bloodPressureLowerColumn.setCellValueFactory(new PropertyValueFactory<>("bloodPressureLower"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        dateTimeColumn.setCellValueFactory(cellData -> {
            LocalDateTime dateTime = cellData.getValue().getDateTime();
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy (H:mm)"));
            return new SimpleStringProperty(formattedDateTime);
        });
    }

    public static void setRecordsTable(TableView<HealthRecord> recordsTable, HealthRecordController healthRecordController) {
        recordsTable.setItems(healthRecordController.getHealthRecords());
    }
}
