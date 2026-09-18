package org.example.csc311_gui_basics;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Hyperlink;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.awt.Desktop;
import java.net.URI;

public class HelloController {
    public record StatRow(String metric, String value, double numericValue) {}

    private final ObservableList<StatRow> barcaStats = FXCollections.observableArrayList(
            new StatRow("Shots Taken", "18", 18),
            new StatRow("La Liga Points", "12", 12),
            new StatRow("Champions League Points", "9", 9),
            new StatRow("Possession (%)", "64", 64),
            new StatRow("Successful Passes", "610", 610),
            new StatRow("Goals Scored", "11", 11),
            new StatRow("Attempts on Goal", "9", 9)
    );

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
    private TableView<StatRow> statsTable;
    @FXML
    private TableColumn<StatRow, String> metricColumn;
    @FXML
    private TableColumn<StatRow, String> valueColumn;
    @FXML
    private BarChart<String, Number> statsChart;
    @FXML
    private Hyperlink subscriptionLink;

    private boolean showingFirstImage = true;

    @FXML
    protected void onHelloButtonClick(ActionEvent e) {
        String n = nameField.getText().trim();
        greetingLabel.setText(n.isEmpty() ? "Please enter a name." : "Hello, " + n + "!");
    }

    @FXML
    protected void onChangeImageClick(ActionEvent e) {
        String crestImg = "https://upload.wikimedia.org/wikipedia/en/4/47/FC_Barcelona_%28crest%29.svg";
        String stadiumImg = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Camp_Nou_aerial_%28cropped%29.jpg/320px-Camp_Nou_aerial_%28cropped%29.jpg";
        showingFirstImage = !showingFirstImage;
        photoView.setImage(new Image(showingFirstImage ? crestImg : stadiumImg, true));
        greetingLabel.setText("Image changed to " + (showingFirstImage ? "Barcelona crest." : "Camp Nou stadium."));
    }

    @FXML
    protected void onClearClick(ActionEvent e) {
        nameField.clear();
        greetingLabel.setText("");
    }

    @FXML
    protected void onOptionChanged(ActionEvent e) {
        String selected = optionsBox.getSelectionModel().getSelectedItem();
        if ("Option 1".equals(selected)) {
            showTable();
        } else if ("Option 2".equals(selected)) {
            showChart();
        }
    }

    @FXML
    protected void onSubscribeToggle(ActionEvent e) {
        boolean subscribed = subscribeCheck.isSelected();
        subscriptionLink.setVisible(subscribed);
        subscriptionLink.setManaged(subscribed);
        if (subscribed) {
            greetingLabel.setText("Subscribed to FC Barcelona updates.");
        } else {
            greetingLabel.setText("Unsubscribed from FC Barcelona updates.");
        }
    }

    @FXML
    public void initialize() {
        optionsBox.getItems().addAll("Option 1", "Option 2");
        optionsBox.getSelectionModel().selectFirst();
        photoView.setImage(new Image("https://upload.wikimedia.org/wikipedia/en/4/47/FC_Barcelona_%28crest%29.svg", true));
        subscriptionLink.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.fcbarcelona.com/en/football/first-team/results"));
            } catch (Exception ex) {
                greetingLabel.setText("Could not open browser for updates link.");
            }
        });

        metricColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().metric()));
        valueColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().value()));
        statsTable.setItems(barcaStats);

        showTable();
    }

    private void showTable() {
        statsTable.setVisible(true);
        statsTable.setManaged(true);
        statsChart.setVisible(false);
        statsChart.setManaged(false);
    }

    private void showChart() {
        statsChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("FC Barcelona Recent Performance");
        for (StatRow stat : barcaStats) {
            series.getData().add(new XYChart.Data<>(stat.metric(), stat.numericValue()));
        }
        statsChart.getData().add(series);

        statsTable.setVisible(false);
        statsTable.setManaged(false);
        statsChart.setVisible(true);
        statsChart.setManaged(true);
    }
}
