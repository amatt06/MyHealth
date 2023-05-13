package rmit.myhealth.backend;

import java.util.ArrayList;
import java.util.List;

public class HealthRecordController {
    private List<HealthRecord> healthRecords;

    public HealthRecordController() {
        healthRecords = new ArrayList<>();
    }

    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
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

