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


    //FXMLLoader theLoader = new FXMLLoader(getClass().getResource("FXML/ClickedOnGame.fxml"));
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

    public Operations() throws IOException {
    }

    @FXML
    void initialize() throws IOException {

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


    private void addImageViewsToMainPage() throws IOException {
        for(int i = 0; i < Database.getUsersGames(Navigator.getUser().getUserID()).size(); i ++) {
            String gameName = Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName();
            String gameType = Database.getGameType(gameName);
            int gameRate = Database.getGameRate(Navigator.getUser().getUserID(), gameName);

            ImageView imageView = new ImageView(Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameImagePath());
            imageView.setFitWidth(100); // Set width
            imageView.setFitHeight(100); // Set height
            imageView.setPreserveRatio(true);
            gridForPhotos.add(imageView, i, 0);



            imageView.setOnMouseClicked(event -> {
                try {
                    updateLabelAndPopUpGameInfo(gameName, gameType, gameRate);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @FXML
    private void updateLabelAndPopUpGameInfo(String gameName, String gameType, int gameRate) throws IOException {
        User theCurrentUser = Navigator.getUser();
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("FXML/ClickedOnGame.fxml"));
        Parent root = null;
        try {
            root = theLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        popupStage.setTitle("Game Details");

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();

        ClickedOnGameController controller = theLoader.getController();
        controller.setNameLabel(gameName);
        controller.setTypeLabel(gameType);
        controller.setRateChoiceBox(gameRate);
        controller.setGameImageView(Database.getGameImage(gameName, theCurrentUser.getUserID()));
        controller.setAverageRateLabel(Database.getGamesAverageRate(gameName));
        controller.setRatedByLabel(Database.getRatedBy(gameName));
    }

    /*public void popUpGameInfo(String popUpTitle) throws IOException {
        Parent root = theLoader.load();

        popupStage.setTitle(popUpTitle);

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }*/

    public void popUp(String resourceTitle, String popUpTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceTitle));
        Parent root = loader.load();
        popupStage.setTitle(popUpTitle);

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

}
