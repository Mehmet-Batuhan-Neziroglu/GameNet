import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import javafx.stage.FileChooser;
import javafx.scene.Group;


public class AddNewGameController {

    ObservableList<String> gameList = FXCollections.observableArrayList();

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
        fileChooser.setTitle("Add your image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));


    }

    @FXML
    void gameNameComboBoxAction(ActionEvent event) {
        gameTypeLabel.setText(Database.getGameType(gameNameComboBox.getValue()));
    }

    @FXML
    void rateGameSliderAction(MouseEvent event) throws SQLException {
        Database.addOrUpdateGameRate(Navigator.getUser().getUserID(), gameNameComboBox.getValue(), (int)rateGameSlider.getValue());
    }

    @FXML
    void saveButtonAction(MouseEvent event) {
        Game game = new Game(gameNameComboBox.getValue(), Database.getGameImage(gameNameComboBox.getValue(), Navigator.getUser().getUserID()));
        Database.saveGame(game, Navigator.getUser().getUserID());
    }

}
