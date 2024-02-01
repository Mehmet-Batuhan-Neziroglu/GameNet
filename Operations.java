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
    private GridPane gridForPhotos;

    @FXML
    private ImageView refreshImage;

    @FXML
    private Button removeButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private ImageView userImage;

    @FXML
    void initialize() {
        addImageViewsToMainPage();
    }
    @FXML
    void addListener(MouseEvent event) throws IOException {
        popUp("FXML/AddNewGame.fxml", "Add Game");
    }

    @FXML
    void profileListener(MouseEvent event) throws IOException {
        popUp("FXML/Profile.fxml", "Profile");
    }

    @FXML
    void refreshListener(MouseEvent event) throws IOException {

        /*FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("FXML/MainPage.fxml"));
        Parent root = rootLoader.load();

        popupStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainScene = new Scene(root);
        popupStage.setScene(mainScene);
        popupStage.show();*/

        addImageViewsToMainPage();

    }

    @FXML
    void removeListener(MouseEvent event) {

    }

    @FXML
    void searchListener(ContextMenuEvent event) {
        //random bir şekilde arama algoritmalarından bir tanesini kullan, elindeki listeyi burada bir ArrayList'e kaydet,
        //sonra da o arraylist'de ismi olan bütün oyunları userGames listesinden al ve onları ekranda görüntüle
    }

    private void addImageViewsToMainPage(){
        for(int i = 0; i < Database.getUsersGames(Navigator.getUser().getUserID()).size(); i ++) {
            ImageView imageView = new ImageView(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameImagePath());
            imageView.setFitWidth(100); // Set width
            imageView.setFitHeight(100); // Set height
            imageView.setPreserveRatio(true);
            gridForPhotos.add(imageView, i, 0);

            imageView.setOnMouseClicked(event -> {
                try {
                    popUp("FXML/ClickedOnGame.fxml", "Game Details");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void popUp(String popUpResource, String popUpTitle) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource(popUpResource));
        Parent root = rootLoader.load();


        popupStage.setTitle(popUpTitle);

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

}
