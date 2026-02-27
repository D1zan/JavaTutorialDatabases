module com.example.tutorial {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tutorial to javafx.fxml;
    exports com.example.tutorial;
}