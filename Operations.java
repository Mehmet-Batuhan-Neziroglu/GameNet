import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import java.io.IOException;

public class Operations{

    private Stage primaryStage;
    private static Stage popupStage = new Stage();

    private Scene mainScene;

    @FXML
    private Button addButton;

    @FXML
    private ScrollPane scrollPane;

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
    private Button logoutButton;

    private Scene scene;
    private Stage stage;
    /*@Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        ProfileController otherClass = new ProfileController(primaryStage);

    }*/

    @FXML
    void initialize() throws IOException {
        Navigator.gamesList = Database.getUsersGames(Navigator.getUser().getUserID());
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
    void searchListener(MouseEvent event) throws IOException {
        //random bir şekilde arama algoritmalarından bir tanesini kullan, elindeki listeyi burada bir ArrayList'e kaydet,
        //sonra da o arraylist'de ismi olan bütün oyunları userGames listesinden al ve onları ekranda görüntüle
        SortingAndSearching.linearSearch(searchBar.getText());
        addImageViewsToMainPage();
    }

    boolean didEntered = false;

    private void addImageViewsToMainPage() throws IOException {
        gridForPhotos.getChildren().clear();
        //Navigator.gamesList = Database.getUsersGames(Navigator.getUser().getUserID());
        for(int i = 0; i < Navigator.gamesList.size(); i ++) {
            //String gameName = Database.getUsersGames(Navigator.getUser().getUserID()).get(i).getGameName();
            //String gameType = Database.getGameType(gameName);
            //String gameRate = Database.getGameRate(Navigator.getUser().getUserID(), gameName);

            String gameName = Navigator.gamesList.get(i).getGameName();
            String gameType = Database.getGameType(gameName);
            String gameRate = Database.getGameRate(Navigator.getUser().getUserID(), gameName);

            ImageView imageView = new ImageView(Navigator.gamesList.get(i).getGameImagePath());
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

    /*private void initializeVBoxes() throws IOException {
        FXMLLoader theLoader = Navigator.clickedOnGameList.get(Navigator.clickedOnGameList.size() - 1);
        ClickedOnGameController controller = theLoader.getController();

        for(int k = 0; k < Database.getUsersGames(Navigator.getUser().getUserID()).size(); k++) {
            String gameName = Database.getUsersGames(Navigator.getUser().getUserID()).get(k).getGameName();
            Database.fillUserIDAndReviewsList(gameName);
            System.out.println("denememmmmm111");
            for (int i = 0; i < Navigator.reviewsList.size(); i++) {
                if (Navigator.reviewsList.get(0) != null) {
                    System.out.println("denememmmmm222");
                    FXMLLoader theLoaderTwo = new FXMLLoader(getClass().getResource("FXML/CommentBox.fxml"));
                    Parent root = theLoaderTwo.load();
                    CommentBoxController cbController = theLoaderTwo.getController();

                    cbController.setNameLabel(Database.getUserNameById(Navigator.userIDListForReviews.get(i)));
                    System.out.println(Database.getUserNameById(Navigator.userIDListForReviews.get(i)));
                    cbController.setRateLabel(Database.getGameRate(Navigator.userIDListForReviews.get(i), gameName));
                    cbController.setCommentLabel(Navigator.reviewsList.get(i));
                    controller.addToTheVBox(cbController.getTheAnchorPane());
                }
            }
        }
    }*/

    /*private void createAndInitializeClickedOnGameControllers(String gameName, String gameType, String gameRate) throws IOException{
        User theCurrentUser = Navigator.getUser();
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("FXML/ClickedOnGame.fxml"));
        Navigator.clickedOnGameList.add(theLoader);

        Parent root = null;
        try {
            root = theLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ClickedOnGameController controller = theLoader.getController();
        controller.setGameNameLabel(gameName);
        controller.setTypeLabel(gameType);
        controller.setRateChoiceBox(gameRate);
        controller.setGameImageView(Database.getGameImage(gameName, theCurrentUser.getUserID()));
        controller.setAverageRateLabel(Database.getGamesAverageRate(gameName));
        controller.setRatedByLabel(Database.getRatedBy(gameName));
    }*/
    @FXML
    private void updateLabelAndPopUpGameInfo(String gameName, String gameType, String gameRate) throws IOException {
        Navigator.reviewsList.clear();
        System.out.println("who is");
        User theCurrentUser = Navigator.getUser();
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("FXML/ClickedOnGame.fxml"));
        Navigator.clickedOnGameList.add(theLoader);


        System.out.println("the king");
        Parent root = null; //NEDEN BU ROOTUN ÖNCELİĞİ VAR? YUKARIDA OLUNCA BURADAN ÇIKIP CLICKEDONGAME INITIALIZER INA GİDİYOR
        try {
            root = theLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("tell me");
        Scene popUpScene = new Scene(root);
        popupStage.setTitle("Game Details");
        popupStage.setScene(popUpScene);
        popupStage.show();

        System.out.println("nasıl");
        ClickedOnGameController controller = theLoader.getController();
        System.out.println("yani");
        controller.setGameNameLabel(gameName);
        controller.setTypeLabel(gameType);
        controller.setRateChoiceBox(gameRate);
        System.out.println("öncelik kimde");
        controller.setGameImageView(Database.getGameImage(gameName, theCurrentUser.getUserID()));
        controller.setAverageRateLabel(Database.getGamesAverageRate(gameName));
        controller.setRatedByLabel(Database.getRatedBy(gameName));

        controller.setVBoxes();
    }

    public void popUp(String resourceTitle, String popUpTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceTitle));
        Parent root = loader.load();
        popupStage.setTitle(popUpTitle);

        Scene popupScene = new Scene(root);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    void logoutButtonListener(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
