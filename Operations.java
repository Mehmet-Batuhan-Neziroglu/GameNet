import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Operations {

    private static Stage popupStage = new Stage();
    private Scene mainScene;

    @FXML
    private Button addButton;

    @FXML
    private GridPane gridForPhotoes;

    @FXML
    private ImageView refreashImage;

    @FXML
    private Button removeButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private ImageView userImage;

    @FXML
    void addListener(MouseEvent event) throws IOException {

        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("AddNewGame.fxml"));
        Parent root = rootLoader.load();


        popupStage.setTitle("Add Game");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void profileListener(MouseEvent event) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        Parent root = rootLoader.load();


        popupStage.setTitle("Profile");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void refreashListener(MouseEvent event) throws IOException {

        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = rootLoader.load();

        popupStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        popupStage.setScene(mainScene);
        popupStage.show();

    }

    @FXML
    void removeListener(MouseEvent event) {

    }

    @FXML
    void searchListener(ContextMenuEvent event) {

    }

}
