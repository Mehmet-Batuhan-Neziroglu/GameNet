import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection
 */
public class DatabaseConnection {
    private static String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11678950";
    private static String user = "sql11678950";
    private static String password = "jT3xDXtjDp";

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