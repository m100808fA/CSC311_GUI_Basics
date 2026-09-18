module org.example.csc311_gui_basics {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.csc311_gui_basics to javafx.fxml;
    exports org.example.csc311_gui_basics;
}