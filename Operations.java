import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    void addLıstener(MouseEvent event) throws IOException {

        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("AddNewGame.fxml"));
        Parent root = rootLoader.load();

        Stage mainStage = new Stage();
        popupStage.setTitle("Add Game");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void profileLıstener(MouseEvent event) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        Parent root = rootLoader.load();

        Stage mainStage = new Stage();
        popupStage.setTitle("Profile");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void refreashLıstener(MouseEvent event) throws IOException {


    }

    @FXML
    void removeLıstener(MouseEvent event) {

    }

    @FXML
    void searchLıstener(ContextMenuEvent event) {

    }

}
