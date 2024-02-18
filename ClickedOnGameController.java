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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ClickedOnGameController {

    private static Stage popupStage = new Stage();
    ObservableList<String> rates = FXCollections.observableArrayList();
    Image image = null;
    FXMLLoader rootLoader;

    @FXML
    private Button addReviewButton;

    @FXML
    private Label averageRateLabel;

    @FXML
    private Label gameNameLabel;

    @FXML
    private Button editButton;

    @FXML
    private ImageView gameImageView;

    @FXML
    private ChoiceBox<String> rateChoiceBox;

    @FXML
    private Label ratedByLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Label typeLabel;

    @FXML
    private Label successfullySavedLabel;

    @FXML
    private VBox ratesAndReviewsVBox;

    boolean didEntered = false;

    @FXML
    void initialize() throws IOException {

        for (int i = 1; i < 6; i++) {
            rates.add(String.valueOf(i));
        }
        rateChoiceBox.setItems(rates);
        gameImageView.setImage(image);
    }

    public void setVBoxes() throws IOException {
        FXMLLoader theLoader = Navigator.clickedOnGameList.get(Navigator.clickedOnGameList.size() - 1);
        ClickedOnGameController controller = theLoader.getController();

        Database.fillUserIDAndReviewsList(controller.gameNameLabel.getText());

        for (int i = 0; i < Navigator.reviewsList.size(); i++) {
            if (Navigator.reviewsList.get(0) != null) {
                FXMLLoader theLoaderTwo = new FXMLLoader(getClass().getResource("FXML/CommentBox.fxml"));
                Parent root = theLoaderTwo.load();
                CommentBoxController cbController = theLoaderTwo.getController();

                cbController.setNameLabel(Database.getUserNameById(Navigator.userIDListForReviews.get(i)));
                cbController.setRateLabel(Database.getGameRate(Navigator.userIDListForReviews.get(i), controller.gameNameLabel.getText()));
                cbController.setCommentLabel(Navigator.reviewsList.get(i));
                controller.addToTheVBox(cbController.getTheAnchorPane());
            }
            }
    }

    @FXML
    void addReviewButtonAction(MouseEvent event) throws IOException {
        rootLoader = new FXMLLoader(getClass().getResource("FXML/AddReview.fxml"));
        Parent root = rootLoader.load();


        popupStage.setTitle("Add Review");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void saveButtonAction(MouseEvent event) throws SQLException {
        Database.addOrUpdateGameRate(Navigator.getUser().getUserID(), gameNameLabel.getText(), rateChoiceBox.getValue());
        rateChoiceBox.setValue(Database.getGameRate(Navigator.getUser().getUserID(), gameNameLabel.getText()));
        averageRateLabel.setText(Database.getGamesAverageRate(gameNameLabel.getText()) + "");
        ratedByLabel.setText(Database.getRatedBy(gameNameLabel.getText()) + "");
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
            Database.addImage(Navigator.getUser().getUserID(), gameNameLabel.getText(), selectedFile.toURI().toString());
            Navigator.gamesList = Database.getUsersGames(Navigator.getUser().getUserID());
        }
    }


    public void setGameNameLabel(String name) {
        gameNameLabel.setText(name);
    }

    public String getGameName() {
        return  gameNameLabel.getText();
    }

    public void setTypeLabel(String type) {
        typeLabel.setText(type);
    }

    public void setRateChoiceBox(String rate){
        rateChoiceBox.setValue(rate);
    }

    public String getRate(){
        return rateChoiceBox.getValue();
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

    public void addToTheVBox(AnchorPane commentBox){
        ratesAndReviewsVBox.getChildren().add(commentBox);
    }
}
