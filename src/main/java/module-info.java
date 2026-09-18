module org.example.csc311_gui_basics {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.csc311_gui_basics to javafx.fxml;
    exports org.example.csc311_gui_basics;
}