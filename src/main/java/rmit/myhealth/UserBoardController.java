package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import rmit.myhealth.model.HealthRecordController;
import rmit.myhealth.model.MyHealth;

import java.io.IOException;

public class UserBoardController {
    @FXML
    private Label welcomeLabel;

    public void setUserDetails(String firstName, String lastName) {
        welcomeLabel.setText(firstName + " " + lastName);
    }

    @FXML
    protected void handleNewRecord() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewRecord.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Create a new stage for the new record form
            Stage newRecordStage = new Stage();
            newRecordStage.setTitle("New Record");
            newRecordStage.setScene(scene);
            newRecordStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewAll() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewRecords.fxml"));
            Parent root = loader.load();
            ViewRecordsController controller = loader.getController();

            // Set the user's health records in the ViewRecordsController
            HealthRecordController healthRecordController = MyHealth.getInstance().getCurrentUser().getHealthRecordController();
            controller.setRecordsTable(healthRecordController.getHealthRecords());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEdit() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditRecord.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            Stage editRecordStage = new Stage();
            editRecordStage.setTitle("Edit Record");
            editRecordStage.setScene(scene);
            editRecordStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}