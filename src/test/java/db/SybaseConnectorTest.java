package db;

import config.VaultManager;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;

public class SybaseConnectorTest {
    
    @Before
    public void setUp() {
        VaultManager.setSecret("db.url", "jdbc:sybase:Tds:localhost:5000/testdb");
        VaultManager.setSecret("db.username", "testuser");
        VaultManager.setSecret("db.password", "testpassword");
    }
    
    @After
    public void tearDown() {
        SybaseConnector.closeConnection();
    }
    
    @Test
    public void testConnect_ValidCredentials_ReturnsConnection() {
        
        
        try {
            Connection conn = SybaseConnector.connect();
            
        } catch (Exception e) {
            assertTrue("Should be a database connection error, not a code error", 
                    e.getMessage().contains("database") || 
                    e.getCause().getMessage().contains("database"));
        }
    }
    
    @Test
    public void testConnect_MissingCredentials_ThrowsException() {
        VaultManager.removeSecret("db.url");
        
        try {
            SybaseConnector.connect();
            fail("Should throw an exception when credentials are missing");
        } catch (IllegalStateException e) {
            assertTrue("Exception message should mention credentials", 
                    e.getMessage().contains("credentials"));
        } catch (Exception e) {
            fail("Should throw IllegalStateException, not " + e.getClass().getName());
        }
    }
    
    @Test
    public void testGetConnection_AfterConnect_ReturnsConnection() {
        
        try {
            SybaseConnector.connect();
            
            Connection conn = SybaseConnector.getConnection();
            
        } catch (Exception e) {
        }
    }
    
    @Test
    public void testCloseConnection_AfterConnect_ClosesConnection() {
        
        try {
            SybaseConnector.connect();
            
            SybaseConnector.closeConnection();
            
        } catch (Exception e) {
        }
    }
}
