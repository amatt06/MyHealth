package rmit.myhealth.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class HealthRecordController {
    private List<HealthRecord> healthRecords;

    public HealthRecordController() {
        healthRecords = new ArrayList<>();
    }

    public ObservableList<HealthRecord> getHealthRecords() {
        return FXCollections.observableList(healthRecords);
    }

    public void addRecord(HealthRecord record) {
        healthRecords.add(record);
    }

    public void editRecord(int index, HealthRecord record) {
        healthRecords.set(index, record);
    }

    public void deleteRecord(int index) {
        healthRecords.remove(index);
    }

    public void exportRecords() {
        // implementation for exporting records
    }
}

