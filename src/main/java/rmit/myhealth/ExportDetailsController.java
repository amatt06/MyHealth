package rmit.myhealth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import javafx.stage.Stage;

import java.io.File;

public class ExportDetailsController {

    private ExportRecordsController exportRecordsController;

    @FXML
    private TextField fileName;

    @FXML
    private TextField filePath;

    @FXML
    private Button chooseLocation;

    @FXML
    private Button cancelButton;

    public void setExportRecordsController(ExportRecordsController exportRecordsController) {
        this.exportRecordsController = exportRecordsController;
    }

    @FXML
    private void chooseLocation() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Location");

        // Set the initial directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show the directory chooser dialog
        Stage stage = (Stage) chooseLocation.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            // Process the selected directory
            String directoryPath = selectedDirectory.getAbsolutePath();
            // Set the directory path in the text field
            filePath.setText(directoryPath);
        }
    }


    // Provide the details to the export handler.
    @FXML
    public void setDetails() {
        String selectedFilePath = filePath.getText();
        String selectedFileName = fileName.getText();

        // Add ".csv" extension if it's not already present
        if (!selectedFileName.toLowerCase().endsWith(".csv")) {
            selectedFileName += ".csv";
        }

        exportRecordsController.setFilePath(selectedFilePath);
        exportRecordsController.setFileName(selectedFileName);
        exportRecordsController.handleExport();
        closeWindow();
    }


    @FXML
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}