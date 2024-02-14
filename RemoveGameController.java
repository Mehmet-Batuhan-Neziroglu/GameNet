import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Collections;

public class RemoveGameController {

    ObservableList<String> usersGameList = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> gameNameComboBox;

    @FXML
    private Button removeButton;

    @FXML
    private Label warningLabel;

    @FXML
    void initialize() {
        for (int i = 0; i < Database.getUsersGames(Navigator.getUser().getUserID()).size(); i++) {
            usersGameList.add(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName());
        }
        //Collections.addAll(Database.getUsersGames(Navigator.getUser().getUserID()));
        gameNameComboBox.setValue("Choose a game");
        gameNameComboBox.setItems(usersGameList);

    }

        @FXML
    void gameNameComboBoxActionListener(ActionEvent event) {

    }

    @FXML
    void removeButtonAction(MouseEvent event) {
        String gameToRemove = gameNameComboBox.getValue();
        if(!gameToRemove.equals("Choose a game")){
            //fill this method
            Database.removeUsersGame(Navigator.getUser().getUserID(), gameToRemove);
        }
        else{
            warningLabel.setTextFill(Color.color(1, 0, 0));
            warningLabel.setText("Please select a game!");
        }
    }

}
