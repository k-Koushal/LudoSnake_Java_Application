module com.example.ludosnake {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ludosnake to javafx.fxml;
    exports com.example.ludosnake;
}