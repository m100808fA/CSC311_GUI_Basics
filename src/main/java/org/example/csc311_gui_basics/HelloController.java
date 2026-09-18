package org.example.csc311_gui_basics;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private TextField nameField;

    @FXML
    private Label greetingLabel;

    @FXML
    private ImageView photoView;

    @FXML
    private CheckBox subscribeCheck;

    @FXML
    private ChoiceBox<String> optionsBox;

    @FXML
    protected void onHelloButtonClick(ActionEvent e) {
        String n = nameField.getText().trim();
        greetingLabel.setText(n.isEmpty() ? "Please enter a name." : "Hello, " + n + "!");
    }

    @FXML
    protected void onChangeImageClick(ActionEvent e) {
        String img1 = "https://via.placeholder.com/100";
        String img2 = "https://via.placeholder.com/100/ff7f7f/333333?text=Hi";
        String current = (photoView.getImage() == null) ? "" : photoView.getImage().getUrl();
        photoView.setImage(new Image(current.equals(img1) ? img2 : img1));
    }

    @FXML
    protected void onClearClick(ActionEvent e) {
        nameField.clear();
        greetingLabel.setText("");
    }

    @FXML
    public void initialize() {
        optionsBox.getItems().addAll("Option 1", "Option 2", "Option 3");
        optionsBox.getSelectionModel().selectFirst();
        photoView.setImage(new Image("https://via.placeholder.com/100"));
    }
}
