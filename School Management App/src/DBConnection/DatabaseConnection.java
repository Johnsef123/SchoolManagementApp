package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    final static private String url="jdbc:mysql://127.0.0.1:3306/school_schema";
    final static private String username="root";
    final static private String password="27012000";

        public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(url, username, password);
        }
}
