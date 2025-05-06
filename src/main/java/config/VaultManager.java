package config;

import java.util.HashMap;
import java.util.Map;

/**
 * Secure vault implementation for managing sensitive credentials
 * In a production environment, this would be connected to a proper secrets management solution
 * like HashiCorp Vault, AWS Secrets Manager, or Azure Key Vault
 */
public class VaultManager {
    private static final Map<String, String> vault = new HashMap<>();
    
    static {
        initializeFromEnvironment();
    }
    
    /**
     * Initializes the vault with values from environment variables
     */
    private static void initializeFromEnvironment() {
        String dbUrl = System.getenv("DB_URL");
        if (dbUrl == null || dbUrl.isEmpty()) {
            // This is just a placeholder - no actual credentials
            dbUrl = "jdbc:sybase:Tds:localhost:5000/mydb";
        }
        vault.put("db.url", dbUrl);
        
        String dbUsername = System.getenv("DB_USERNAME");
        if (dbUsername == null || dbUsername.isEmpty()) {
            // This is just a placeholder - no actual credentials
            dbUsername = "placeholder_username";
        }
        vault.put("db.username", dbUsername);
        
        String dbPassword = System.getenv("DB_PASSWORD");
        if (dbPassword == null || dbPassword.isEmpty()) {
            // This is just a placeholder - no actual credentials
            dbPassword = "placeholder_password";
        }
        vault.put("db.password", dbPassword);
    }
    
    /**
     * Gets a secret from the vault
     * @param key The secret key
     * @return The secret value or null if not found
     */
    public static String getSecret(String key) {
        return vault.get(key);
    }
    
    /**
     * Sets a secret in the vault
     * @param key The secret key
     * @param value The secret value
     */
    public static void setSecret(String key, String value) {
        vault.put(key, value);
    }
    
    /**
     * Checks if a secret exists in the vault
     * @param key The secret key
     * @return true if the secret exists, false otherwise
     */
    public static boolean hasSecret(String key) {
        return vault.containsKey(key);
    }
    
    /**
     * Removes a secret from the vault
     * @param key The secret key
     * @return true if the secret was removed, false if it didn't exist
     */
    public static boolean removeSecret(String key) {
        if (!vault.containsKey(key)) {
            return false;
        }
        vault.remove(key);
        return true;
    }
}
