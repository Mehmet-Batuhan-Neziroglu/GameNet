import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection
 */
public class DatabaseConnection {
    private static String url = "jdbc:sqlserver://localhost;database=XChange;encryption=true;trustServerCertificate=true;";
    private static String user = "sa";
    private static String password = "1";

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