module com.example.lab03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    exports lab3;
    opens lab3 to javafx.fxml;
}