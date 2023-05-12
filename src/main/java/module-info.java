module rmit.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens rmit.myhealth to javafx.fxml;
    exports rmit.myhealth;
}