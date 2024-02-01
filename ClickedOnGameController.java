import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ClickedOnGameController {

    private static Stage popupStage = new Stage();
    ObservableList<Integer> rates = FXCollections.observableArrayList();

    @FXML
    private Button addReviewButton;

    @FXML
    private Label averageRateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ChoiceBox<Integer> rateChoiceBox;

    @FXML
    private Label ratedByLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Label typeLabel;

    @FXML
    void initialize() {
        for (int i = 1; i < 6; i++) {
            rates.add(i);
        }
        rateChoiceBox.setValue(1); //bunun içine user'ın oyuna verdiği current value yazacak
        rateChoiceBox.setItems(rates);
    }

    @FXML
    void addReviewButtonAction(MouseEvent event) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("FXML/AddReview.fxml"));
        Parent root = rootLoader.load();


        popupStage.setTitle("Add Review");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void saveButtonAction(MouseEvent event) {

    }

}
