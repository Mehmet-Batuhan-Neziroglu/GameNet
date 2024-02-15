import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class Database {

    private static Connection connection = DatabaseConnection.connectDatabase();

    public static int getMaxID() {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT MAX(UserID) FROM Users";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int maxID = rs.getInt(1);
                return maxID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void insertNewUser(User user) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO Users (UserID, UserName, UserPassword, FirstName, LastName, MothersName, FavouriteColor)"
                       + "SELECT " + user.getUserID() + ", '" + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getMothersName() + "', '" + user.getFavouriteColor() + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String userName) {
        int userID = 0;
        String password = "";
        String firstName = "";
        String lastName = "";
        String mothersName = "";
        String favouriteColor = "";

        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM Users WHERE UserName = '" + userName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userID = rs.getInt(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                firstName = rs.getString(4);
                lastName = rs.getString(5);
                mothersName = rs.getString(6);
                favouriteColor = rs.getString(7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Game> games = getUsersGames(userID);
        return new User(userID, userName, password, firstName, lastName, mothersName, favouriteColor, games);
    }

    public static String getUserNameById(int userID){
        String usrName = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT UserName FROM Users WHERE UserID = '" + userID + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                usrName = rs.getString(1);  //bu index 1 idi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usrName;
    }

    public static String getGameImage(String gameName, int userID) {
        String gameImagePath = "";
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT GameImagePath FROM UsersGames WHERE UserID = '" + userID + "' AND GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                gameImagePath = rs.getString(1);  //bu index 1 idi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameImagePath;
    }

    //saveImage method is required
    public static void addImage(int userId, String gameName, String gameImagePath) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE UsersGames SET GameImagePath = '" + gameImagePath + "' WHERE UserID = '" + userId + "' AND GameName = '" + gameName + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getGames() {
        ArrayList<String> games = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT GameName FROM Games";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                games.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static String getGameType(String gameName) {
        String type = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT GamesType FROM Games WHERE GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                type = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }


    public static void updateUserName(int userID, String userName) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE Users SET UserName = '" + userName + "' WHERE UserID = " + userID;
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePassword(int userID, String password) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE Users SET UserPassword = '" + password + "' WHERE UserID = " + userID;
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
       }
    }

    public static void updatePassword(String userName, String password) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE Users SET UserPassword = '" + password + "' WHERE UserName = '" + userName + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFirstName(int userID, String firstName) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE Users SET FirstName = '" + firstName + "' WHERE UserID = " + userID;
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLastName(int userID, String lastName) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE Users SET LastName = '" + lastName + "' WHERE UserID = " + userID;
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Check if username exists
    public static boolean checkUsername(String userName) {
        ArrayList<String> userNames = new ArrayList<String>();
        boolean userExists = false;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT UserName FROM Users";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String str : userNames) {
            if (str.equals(userName)) {
                userExists = true;
                break;
            }
        }
        return userExists;
    }

    //Check if password is correct
    public static boolean checkPassword(String userName, String password) {
        String userPassword = "";
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT UserPassword FROM Users WHERE UserName = '" + userName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userPassword = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password.equals(userPassword);
    }

    //check if mothers name is correct
    public static boolean checkMothersName(String userName, String mothersName) {
        String userMothersName = "";
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT MothersName FROM Users WHERE UserName = '" + userName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userMothersName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mothersName.equals(userMothersName);
    }

    //check if favourite color is correct
    public static boolean checkFavouriteColor(String userName, String favouriteColor) {
        String userFavouriteColor = "";
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT FavouriteColor FROM Users WHERE UserName = '" + userName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userFavouriteColor = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favouriteColor.equals(userFavouriteColor);
    }

    public static boolean checkIsGameInUsersGame(String gameName){
        ArrayList<Game> arr = getUsersGames(Navigator.getUser().getUserID());

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getGameName().equals(gameName)) {
                return true;
            }
        }

        return  false;
    }

    public static ArrayList<Game> getUsersGames(int userID) {
        ArrayList<Game> games = new ArrayList<Game>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM UsersGames WHERE UserID = " + userID;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String gameName = rs.getString(2);
                String gameImagePath = rs.getString(3);
                Game game = new Game(gameName, gameImagePath);
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static void saveGame(Game game, int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO UsersGames (UserID, GameName, gameImagePath)"
                       + "SELECT " + userID + ", '" + game.getGameName() + "', '" + game.getGameImagePath() + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeUsersGame(String gameName, int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "DELETE FROM UsersGames WHERE GameName = '" + gameName + "' AND UserID = '" + userID + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Navigator.gamesList.clear();
        Navigator.gamesList = Database.getUsersGames(Navigator.getUser().getUserID());

    }

    public static void addReview(int userID, String gameName, String review){
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO Reviews (UserID, GameName, Review)"
                    + "SELECT " + userID + ", '" + gameName + "', '" + review + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static String getReview(int userId, String gameName){
        String review = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT Review FROM Reviews WHERE GameName = '" + gameName + "' AND UserID = '" + userId + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                review = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    public  static void fillUserIDAndReviewsList(String gameName){
        int userId = 0;
        String review = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT UserID, Review FROM Reviews WHERE GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            System.out.println("not even here!?");
            while (rs.next()) {
                System.out.println("do yo enter here");
                userId = rs.getInt("UserID");
                review = rs.getString("Review");

                Navigator.userIDListForReviews.add(userId);
                Navigator.reviewsList.add(review);
                System.out.println("or hereee");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public  static String getGameRate(int userId, String gameName){
        String rate = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT Rate FROM Rates WHERE GameName = '" + gameName + "' AND UserID = '" + userId + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rate = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rate;
    }

    public static int getRatedBy(String gameName){
        int ratedBy = 0;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT RatedBy FROM Games WHERE GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ratedBy = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratedBy;
    }

    public static double getGamesAverageRate(String gameName) {
        double averageRate = 0;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT GamesAverageRate FROM Games WHERE GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                averageRate = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageRate;
    }


    public static void addOrUpdateGameRate(int userID, String gameName, String gameRate) throws SQLException {
        Locale.setDefault(Locale.US);

        int ratedBy = 0;
        int counter = 0;
        double total = 0;
        double average = 0;
        try {
            Statement st = connection.createStatement();
            //burası nedense hata vermiyor ve yeni rate kaydetmek istediğimde de buraya giriyor
            String sql = "SELECT Rate FROM Rates WHERE UserID = '" + userID + "' AND GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                sql = "UPDATE Rates SET Rate = '" + gameRate + "' WHERE UserID = '" + userID + "' AND GameName = '" + gameName + "'";
                st.execute(sql);
            }
            else{
                throw new SQLException();
            }

        } catch (SQLException e) {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO Rates (UserID, GameName, Rate)"
                    + "SELECT " + userID + ", '" + gameName + "', '" + gameRate + "'";
            st.execute(sql);
            sql = "SELECT RatedBy FROM Games WHERE GameName = '" + gameName + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ratedBy = rs.getInt(1);
            }
            ratedBy++;
            sql ="UPDATE Games SET RatedBy = '" + ratedBy + "' WHERE GameName = '" + gameName + "'";
            st.execute(sql);
            //burada ne yapıyoruz ya (316'dan itibaren) , ratedBy değerini bir user'ın rates'ine kaydediyoruz????????
        }

        Statement st = connection.createStatement();
        String sql = "SELECT Rate FROM Rates WHERE GameName = '" + gameName + "'";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            double columnValue = rs.getDouble("Rate");
            total += columnValue;
            counter++;
        }
        System.out.println(total);
        average = total/counter;
        String formattedValue = String.format("%.2f", average);
        System.out.println("Formatted val: " + formattedValue);

        sql = "UPDATE Games SET GamesAverageRate = '" + formattedValue + "' WHERE GameName = '" + gameName + "'";
        st.execute(sql);

    }












}
