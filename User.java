import java.util.ArrayList;

/**
 * User
 */
public class User {

    private int userID;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String mothersName;
    private String favouriteColor;
    private ArrayList<Game> games;

    //constructor for first creation
    public User(String userName, String password, String firstName, String lastName, String mothersName, String favouriteColor) {
        this.userID = Database.getMaxID() + 1;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mothersName = mothersName;
        this.favouriteColor = favouriteColor;
        games = new ArrayList<Game>();
        Database.insertNewUser(this);
    }

    //constructor for getting user from database
    public User(int userId, String userName, String password, String firstName, String lastName, String mothersName, String favouriteColor, ArrayList<Game> games) {
        this.userID = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mothersName = mothersName;
        this.favouriteColor = favouriteColor;
        this.games = games;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        Database.updateUserName(userID, userName);
    }

    public void setPassword(String password) {
        this.password = password;
        Database.updatePassword(userID, password);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        Database.updateFirstName(userID, firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        Database.updateLastName(userID, lastName);
    }


    public void addGraph(Game game) {
        games.add(game);
    }

    public String getFavouriteColor() {
        return favouriteColor;
    }

    public String getFirstName() {
        return firstName;
    }

    public ArrayList<Game> getGraphs() {
        return games;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }


    //returns true if another graph with same name exist
    public boolean checkGameName(String gameName) {
        for (Game game : games) {
            if (game.getGameName().equals(gameName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteGame(String gameName, int userID) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameName().equals(gameName)) {
                games.remove(i);
            }
        }
        Database.deleteGame(gameName, userID);
    }

}