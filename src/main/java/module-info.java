module rmit.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens rmit.myhealth to javafx.fxml;
    exports rmit.myhealth;
    exports rmit.myhealth.model;
    opens rmit.myhealth.model to javafx.fxml;
}