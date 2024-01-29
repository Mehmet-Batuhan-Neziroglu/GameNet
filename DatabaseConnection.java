import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection
 */
public class DatabaseConnection {
    private static String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11680645";
    private static String user = "sql11680645";
    private static String password = "psPfTUfAlI";

    public static Connection connectDatabase() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}