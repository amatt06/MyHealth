package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class UserBoardController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private ImageView profilePictureImageView;

    // Method to set the user's name.
    public void setUserDetails(String firstName, String lastName) {
        welcomeLabel.setText(firstName + " " + lastName);
    }

    // Method to set the profile picture
    public void setProfilePicture(Image profilePicture) {
        profilePictureImageView.setImage(profilePicture);
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
            controller.initialise();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRecord.fxml"));
            Parent root = loader.load();
            EditRecordController controller = loader.getController();
            controller.initialise();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecord.fxml"));
            Parent root = loader.load();
            DeleteRecordController controller = loader.getController();
            controller.initialise();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
