import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;

/**
 * This class contains test cases for the functionality of the HealthRecordController class.
 * The tests cover adding, editing, deleting and retrieving health records from the controller.
 */

class HealthRecordControllerTest {

    // Test the successful addition of a record.
    @Test
    void testAddRecord() {
        HealthRecordController controller = new HealthRecordController();
        HealthRecord record = new HealthRecord(70.0, 36.6, 120, 90, "Note", LocalDateTime.now());
        controller.addRecord(record);
        List<HealthRecord> records = controller.getHealthRecords();
        assertEquals(1, records.size());
        assertEquals(record, records.get(0));
    }

    // Test the result of editing/overwriting a record.
    @Test
    void testEditRecord() {
        HealthRecordController controller = new HealthRecordController();
        HealthRecord record = new HealthRecord(70.0, 36.6, 32.4, 13.6, "New Note", LocalDateTime.now());
        controller.addRecord(record);
        HealthRecord newRecord = new HealthRecord(75.0, 36.5, 130.4, 90.0, "New Note", LocalDateTime.now());
        controller.editRecord(0, newRecord);
        List<HealthRecord> records = controller.getHealthRecords();
        assertEquals(1, records.size());
        assertEquals(newRecord, records.get(0));
    }

    // Test the successful deletion of a record.
    @Test
    void testDeleteRecord() {
        HealthRecordController controller = new HealthRecordController();
        HealthRecord record1 = new HealthRecord(75.0, 36.5, 130.4, 90.0, "New Note", LocalDateTime.now());
        HealthRecord record2 = new HealthRecord(75.0, 36.5, 130.4, 90.0, "New Note", LocalDateTime.now());
        controller.addRecord(record1);
        controller.addRecord(record2);
        controller.deleteRecord(record1);
        List<HealthRecord> records = controller.getHealthRecords();
        assertEquals(1, records.size());
        assertEquals(record2, records.get(0));
    }

    // Test the retrieval of health records from the controller.
    @Test
    void testGetHealthRecords() {
        HealthRecordController controller = new HealthRecordController();
        List<HealthRecord> expectedRecords = new ArrayList<>();
        expectedRecords.add(new HealthRecord(70.0, 37.0, 120.0, 80.0, "Healthy",
                LocalDateTime.of(2022, 1, 1, 8, 0)));
        expectedRecords.add(new HealthRecord(75.0, 36.5, 115.0, 75.0, "Feeling good",
                LocalDateTime.of(2022, 2, 1, 8, 0)));
        expectedRecords.add(new HealthRecord(72.5, 37.5, 130.0, 90.0, "Slight fever",
                LocalDateTime.of(2022, 3, 1, 8, 0)));
        controller.addRecord(expectedRecords.get(0));
        controller.addRecord(expectedRecords.get(1));
        controller.addRecord(expectedRecords.get(2));

        List<HealthRecord> actualRecords = controller.getHealthRecords();

        assertEquals(expectedRecords.size(), actualRecords.size());
        for (int i = 0; i < expectedRecords.size(); i++) {
            HealthRecord expectedRecord = expectedRecords.get(i);
            HealthRecord actualRecord = actualRecords.get(i);
            assertEquals(expectedRecord.getWeight(), actualRecord.getWeight());
            assertEquals(expectedRecord.getTemperature(), actualRecord.getTemperature());
            assertEquals(expectedRecord.getBloodPressureUpper(), actualRecord.getBloodPressureUpper());
            assertEquals(expectedRecord.getBloodPressureLower(), actualRecord.getBloodPressureLower());
            assertEquals(expectedRecord.getNote(), actualRecord.getNote());
            assertEquals(expectedRecord.getDateTime(), actualRecord.getDateTime());
        }
    }
}
