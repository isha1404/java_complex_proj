package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class SybaseConnector {
    public static void connect() {
        try {
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:sybase:Tds:localhost:5000/mydb", "user", "password");
            System.out.println("Connected to Sybase!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}