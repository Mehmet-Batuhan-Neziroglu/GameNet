import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ClickedOnGameController {

    private static Stage popupStage = new Stage();
    ObservableList<Integer> rates = FXCollections.observableArrayList();
    Image image = null;

    @FXML
    private Button addReviewButton;

    @FXML
    private Label averageRateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button editButton;

    @FXML
    private ImageView gameImageView;

    @FXML
    private ChoiceBox<Integer> rateChoiceBox;

    @FXML
    private Label ratedByLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Label typeLabel;

    @FXML
    private Label successfullySavedLabel;

    @FXML
    void initialize() {
        for (int i = 1; i < 6; i++) {
            rates.add(i);
        }
        rateChoiceBox.setItems(rates);
        gameImageView.setImage(image);
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
    void saveButtonAction(MouseEvent event) throws SQLException {
        //oyun ismini nasıl alabileceğimizi bulmalıyız
        Database.addOrUpdateGameRate(Navigator.getUser().getUserID(), nameLabel.getText(), rateChoiceBox.getValue());
        rateChoiceBox.setValue(Database.getGameRate(Navigator.getUser().getUserID(), nameLabel.getText()));
        successfullySavedLabel.setTextFill(Color.color(0, 1, 0));
        successfullySavedLabel.setText("Successfully Saved");
    }

    @FXML
    void editButtonAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png") //, "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            image = new Image(selectedFile.toURI().toString());
            gameImageView.setImage(image);
            Database.addImage(Navigator.getUser().getUserID(), nameLabel.getText(), selectedFile.toURI().toString());
        }
    }


    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    public void setTypeLabel(String type) {
        typeLabel.setText(type);
    }

    public void setRateChoiceBox(int rate){
        rateChoiceBox.setValue(rate);
    }

    public  void setGameImageView(String imageUrl){
        if (imageUrl != null && !imageUrl.isEmpty()) {
            image = new Image(imageUrl);
            gameImageView.setImage(image);
        }
    }

    public void setAverageRateLabel(double averageRate){
        averageRateLabel.setText(averageRate + "");
    }

    public void setRatedByLabel(int ratedBy){
        ratedByLabel.setText(ratedBy + "");
    }

}
