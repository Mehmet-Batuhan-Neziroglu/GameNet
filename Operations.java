import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

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
    private Label theChooserLabel;

    @FXML
    private ImageView refreshImage;

    @FXML
    private Button removeButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private Button sortButton;

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
        theChooserLabel.setText("");
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
        initialize();

    }

    @FXML
    void removeListener(MouseEvent event) throws IOException {
        popUp("FXML/RemoveGame.fxml", "Remove Game");
    }

    @FXML
    void searchListener(MouseEvent event) throws IOException {
        //random bir şekilde arama algoritmalarından bir tanesini kullan, elindeki listeyi burada bir ArrayList'e kaydet,
        //sonra da o arraylist'de ismi olan bütün oyunları userGames listesinden al ve onları ekranda görüntüle
        Random rand = new Random();
        int theNumber = rand.nextInt(3);

        if(!searchBar.getText().isEmpty()) {
            if (theNumber == 1) {
                SortingAndSearching.linearSearch(searchBar.getText());
                theChooserLabel.setText("The Linear Search is used while searching");
            } else if (theNumber == 2) {
                SortingAndSearching.binarySearch(searchBar.getText(), 0, 0);
                theChooserLabel.setText("The Binary Search is used while searching");
            }
        }

        addImageViewsToMainPage();
    }

    @FXML
    void sortListener(MouseEvent event) throws IOException {

        Random rand = new Random();

        int theNumber = rand.nextInt(3,6);

        if (theNumber == 3) {
            SortingAndSearching.bubbleSort(Navigator.gamesList);
            theChooserLabel.setText("The Bubble Sort is used while sorting");
        }
        else if (theNumber == 4) {
            SortingAndSearching.selectionSort(Navigator.gamesList);
            theChooserLabel.setText("The Selection Sort is used while sorting");
        }
        else if(theNumber == 5){
            SortingAndSearching.insertionSort(Navigator.gamesList);
            theChooserLabel.setText("The Insertion Sort is used while sorting");
        }

        addImageViewsToMainPage();
    }

    boolean didEntered = false;

    private void addImageViewsToMainPage() throws IOException {
        gridForPhotos.getChildren().clear();
        for(int i = 0; i < Navigator.gamesList.size(); i ++) {

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

    @FXML
    private void updateLabelAndPopUpGameInfo(String gameName, String gameType, String gameRate) throws IOException {
        Navigator.reviewsList.clear();
        Navigator.clickedOnGameList.clear();


        User theCurrentUser = Navigator.getUser();
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("FXML/ClickedOnGame.fxml"));
        Navigator.clickedOnGameList.add(theLoader);



        Parent root = null;
        try {
            root = theLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Scene popUpScene = new Scene(root);
        popupStage.setTitle("Game Details");
        popupStage.setScene(popUpScene);
        popupStage.show();


        ClickedOnGameController controller = theLoader.getController();
        controller.setGameNameLabel(gameName);
        controller.setTypeLabel(gameType);
        controller.setRateChoiceBox(gameRate);
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
