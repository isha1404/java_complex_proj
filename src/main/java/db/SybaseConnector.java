package db;

import java.sql.Connection;
import java.sql.DriverManager;
import config.VaultManager;

public class SybaseConnector {
    private static Connection connection;
    
    /**
     * Connects to the Sybase database using credentials from the vault
     * @return The database connection
     */
    public static Connection connect() {
        try {
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            String url = VaultManager.getSecret("db.url");
            String username = VaultManager.getSecret("db.username");
            String password = VaultManager.getSecret("db.password");
            
            if (url == null || username == null || password == null) {
                throw new IllegalStateException("Database credentials not found in vault");
            }
            
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Sybase!");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Gets the current database connection
     * @return The current connection or null if not connected
     */
    public static Connection getConnection() {
        return connection;
    }
    
    /**
     * Closes the database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Database connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
