package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecord;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.io.IOException;

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

    public void initialise() {
        RecordTable.setupTableColumns(recordsTable, weightColumn, temperatureColumn, bloodPressureUpperColumn, bloodPressureLowerColumn, noteColumn, dateTimeColumn);

        HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
        RecordTable.setRecordsTable(recordsTable, healthRecordController);

        // Add row click event handler for double click
        recordsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
                if (selectedRecord != null) {
                    openEditRecordWindow(selectedRecord);
                }
            }
        });
    }

    public void openEditRecordWindow(HealthRecord record) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDetails.fxml"));
            Parent root = loader.load();
            EditDetailsController controller = loader.getController();
            controller.setRecord(record);

            Stage stage = new Stage();
            stage.setTitle("Edit Record");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
