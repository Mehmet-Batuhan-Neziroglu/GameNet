import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Navigator
 */
public class Navigator extends Application{

    public static ArrayList<FXMLLoader> clickedOnGameList  = new ArrayList<>();
    public static ArrayList<FXMLLoader> commentBoxList  = new ArrayList<>();
    public static ArrayList<String> reviewsList  = new ArrayList<>();
    public static ArrayList<Integer> userIDListForReviews = new ArrayList<>();

    private static User user;

    private static ArrayList<FXMLLoader> arr;
    private static ArrayList<String> games = Database.getGames();
    private static boolean isSignIn;

    @Override
    public void start(Stage stage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            isSignIn = true;
        } catch(Exception e) {
            e.printStackTrace();
           }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Navigator.user = user;
    }

    public static ArrayList<String> getGames() {
        return games;
    }
    
    public static boolean getIsSignIn() {
        return isSignIn;
    }

    public static void setIsSignIn(boolean isSign){
         isSignIn = isSign;
    }
    
}