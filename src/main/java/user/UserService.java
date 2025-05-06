package user;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for managing user operations
 */
public class UserService {
    private static Map<String, String> users = new HashMap<>();
    
    /**
     * Authenticates a user with the given credentials
     * @param username The username
     * @param password The password
     * @return true if authentication is successful, false otherwise
     */
    public static boolean authenticate(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    
    /**
     * Registers a new user with the given credentials
     * @param username The username
     * @param password The password
     * @return true if registration is successful, false if user already exists
     */
    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        return true;
    }
    
    /**
     * Removes a user from the system
     * @param username The username
     * @return true if removal is successful, false if user doesn't exist
     */
    public static boolean removeUser(String username) {
        if (!users.containsKey(username)) {
            return false;
        }
        users.remove(username);
        return true;
    }
    
    /**
     * Checks if a user exists
     * @param username The username
     * @return true if the user exists, false otherwise
     */
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    /**
     * Gets the number of registered users
     * @return The number of users
     */
    public static int getUserCount() {
        return users.size();
    }
    
    /**
     * Clears all users (for testing purposes)
     */
    public static void clearUsers() {
        users.clear();
    }
}
