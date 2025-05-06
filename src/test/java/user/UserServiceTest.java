package user;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceTest {
    
    @Before
    public void setUp() {
        UserService.clearUsers();
        UserService.register("testuser", "password123");
    }
    
    @Test
    public void testAuthenticate_ValidCredentials_ReturnsTrue() {
        String username = "testuser";
        String password = "password123";
        
        boolean result = UserService.authenticate(username, password);
        
        assertTrue("Authentication should succeed with valid credentials", result);
    }
    
    @Test
    public void testAuthenticate_InvalidPassword_ReturnsFalse() {
        String username = "testuser";
        String password = "wrongpassword";
        
        boolean result = UserService.authenticate(username, password);
        
        assertFalse("Authentication should fail with invalid password", result);
    }
    
    @Test
    public void testAuthenticate_NonExistingUser_ReturnsFalse() {
        String username = "nonexistinguser";
        String password = "anypassword";
        
        boolean result = UserService.authenticate(username, password);
        
        assertFalse("Authentication should fail for non-existing user", result);
    }
    
    @Test
    public void testRegister_NewUser_ReturnsTrue() {
        String username = "newuser";
        String password = "newpassword";
        
        boolean result = UserService.register(username, password);
        
        assertTrue("Registration should succeed for new user", result);
        assertTrue("New user should be able to authenticate", 
                UserService.authenticate(username, password));
    }
    
    @Test
    public void testRegister_ExistingUser_ReturnsFalse() {
        String username = "testuser"; // Already exists from setUp
        String password = "anotherpassword";
        
        boolean result = UserService.register(username, password);
        
        assertFalse("Registration should fail for existing user", result);
    }
    
    @Test
    public void testRemoveUser_ExistingUser_ReturnsTrue() {
        String username = "testuser";
        
        boolean result = UserService.removeUser(username);
        
        assertTrue("Removal should succeed for existing user", result);
        assertFalse("Removed user should not be able to authenticate", 
                UserService.authenticate(username, "password123"));
    }
    
    @Test
    public void testRemoveUser_NonExistingUser_ReturnsFalse() {
        String username = "nonexistinguser";
        
        boolean result = UserService.removeUser(username);
        
        assertFalse("Removal should fail for non-existing user", result);
    }
    
    @Test
    public void testUserExists_ExistingUser_ReturnsTrue() {
        String username = "testuser";
        
        boolean result = UserService.userExists(username);
        
        assertTrue("Should return true for existing user", result);
    }
    
    @Test
    public void testUserExists_NonExistingUser_ReturnsFalse() {
        String username = "nonexistinguser";
        
        boolean result = UserService.userExists(username);
        
        assertFalse("Should return false for non-existing user", result);
    }
    
    @Test
    public void testGetUserCount_AfterRegistration_ReturnsCorrectCount() {
        UserService.register("user2", "password2");
        UserService.register("user3", "password3");
        
        int count = UserService.getUserCount();
        
        assertEquals("Should return the correct number of users", 3, count);
    }
    
    @Test
    public void testGetUserCount_AfterRemoval_ReturnsCorrectCount() {
        UserService.register("user2", "password2");
        UserService.register("user3", "password3");
        UserService.removeUser("user2");
        
        int count = UserService.getUserCount();
        
        assertEquals("Should return the correct number of users after removal", 2, count);
    }
    
    @Test
    public void testClearUsers_AfterRegistration_RemovesAllUsers() {
        UserService.register("user2", "password2");
        
        UserService.clearUsers();
        int count = UserService.getUserCount();
        
        assertEquals("Should have zero users after clearing", 0, count);
        assertFalse("Users should not exist after clearing", 
                UserService.userExists("testuser") || UserService.userExists("user2"));
    }
}
