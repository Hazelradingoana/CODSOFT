package QuizApp;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:database.db"; // Use your desired database name here

    public static String success = "database_cnt_error.Proceeding in Offline Mode";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);

            // Check if the database file exists
            File dbFile = new File("database.db");

            if (!dbFile.exists()) {
                // If the database file doesn't exist, create it
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Database created successfully.");
            } else {
                // If the database file exists, connect to it
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected to database.");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load JDBC driver.", e);
        }

        return connection;
    }
}
