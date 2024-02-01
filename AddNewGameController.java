import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.Group;


public class AddNewGameController {

    ObservableList<String> gameList = FXCollections.observableArrayList();

    @FXML
    private Label addNewGameErrorLabel;

    @FXML
    private Button addImageButton;

    @FXML
    private ImageView gameImage;

    @FXML
    private ComboBox<String> gameNameComboBox;

    @FXML
    private Label gameTypeLabel;

    @FXML
    private Slider rateGameSlider;

    @FXML
    private Button saveButton;

    Image image = null;

    @FXML
    void initialize() {
        for (int i = 0; i < Navigator.getGames().size(); i++) {
            gameList.add(Navigator.getGames().get(i));
        }

        gameNameComboBox.setValue("Choose a game");
        gameNameComboBox.setItems(gameList);
    }
    @FXML
    void addImageButtonAction(ActionEvent event) {
        //imageView kutucuğuna user'ın seçtiği image eklenecek. Ancak bunu yapabilmek için butonun bizi bilgisayarın dosyalar popup ına yönlendirmesi gerekiyor
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png") //, "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            image = new Image(selectedFile.toURI().toString());
            gameImage.setImage(image);
            //Database.addImage(Navigator.getUser().getUserID(), gameNameComboBox.getValue(), selectedFile.toURI().toString());
        }
        else{
            addNewGameErrorLabel.setTextFill(Color.color(1, 0, 0));
            addNewGameErrorLabel.setText("Please choose an image");
        }

    }

    @FXML
    void gameNameComboBoxAction(ActionEvent event) {
        gameTypeLabel.setText(Database.getGameType(gameNameComboBox.getValue()));
    }

    @FXML
    void rateGameSliderAction(MouseEvent event) throws SQLException {
        //Database.addOrUpdateGameRate(Navigator.getUser().getUserID(), gameNameComboBox.getValue(), (int)rateGameSlider.getValue());
        System.out.println("aaaaaaa");
    }


    @FXML
    void saveButtonAction(MouseEvent event) {
        ArrayList<Game> usersGames = Database.getUsersGames(Navigator.getUser().getUserID());
        boolean isExists = false;

        for (Game game: usersGames){
            if(game.getGameName().equals(gameNameComboBox.getValue())){
                isExists = true;
            }
        }
        if(isExists == false) {
            Game game = new Game(gameNameComboBox.getValue(), Database.getGameImage(gameNameComboBox.getValue(), Navigator.getUser().getUserID()));
            Database.saveGame(game, Navigator.getUser().getUserID());
            addNewGameErrorLabel.setTextFill(Color.color(0, 1, 0));
            addNewGameErrorLabel.setText("The game is successfully added to your list");
            Database.addImage(Navigator.getUser().getUserID(), gameNameComboBox.getValue(), image.getUrl());
        }
        else{
            addNewGameErrorLabel.setTextFill(Color.color(1, 0, 0));
            addNewGameErrorLabel.setText("The game you are trying to add already exists in your games table");
        }

        System.out.println("Burada calısıyor mu");


    }

}
