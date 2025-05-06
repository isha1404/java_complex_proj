package config;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VaultManagerTest {
    
    @Before
    public void setUp() {
        VaultManager.setSecret("test.key", "test.value");
    }
    
    @Test
    public void testGetSecret_ExistingKey_ReturnsValue() {
        String key = "test.key";
        String expectedValue = "test.value";
        
        String actualValue = VaultManager.getSecret(key);
        
        assertEquals("Should return the correct secret value", expectedValue, actualValue);
    }
    
    @Test
    public void testGetSecret_NonExistingKey_ReturnsNull() {
        String key = "non.existing.key";
        
        String value = VaultManager.getSecret(key);
        
        assertNull("Should return null for non-existing key", value);
    }
    
    @Test
    public void testSetSecret_NewKey_StoresValue() {
        String key = "new.key";
        String value = "new.value";
        
        VaultManager.setSecret(key, value);
        
        assertEquals("Should store and retrieve the new value", value, VaultManager.getSecret(key));
    }
    
    @Test
    public void testSetSecret_ExistingKey_UpdatesValue() {
        String key = "test.key";
        String newValue = "updated.value";
        
        VaultManager.setSecret(key, newValue);
        
        assertEquals("Should update and retrieve the new value", newValue, VaultManager.getSecret(key));
    }
    
    @Test
    public void testHasSecret_ExistingKey_ReturnsTrue() {
        String key = "test.key";
        
        boolean result = VaultManager.hasSecret(key);
        
        assertTrue("Should return true for existing key", result);
    }
    
    @Test
    public void testHasSecret_NonExistingKey_ReturnsFalse() {
        String key = "non.existing.key";
        
        boolean result = VaultManager.hasSecret(key);
        
        assertFalse("Should return false for non-existing key", result);
    }
    
    @Test
    public void testRemoveSecret_ExistingKey_RemovesAndReturnsTrue() {
        String key = "test.key";
        
        boolean result = VaultManager.removeSecret(key);
        
        assertTrue("Should return true when removing existing key", result);
        assertNull("Secret should be removed", VaultManager.getSecret(key));
    }
    
    @Test
    public void testRemoveSecret_NonExistingKey_ReturnsFalse() {
        String key = "non.existing.key";
        
        boolean result = VaultManager.removeSecret(key);
        
        assertFalse("Should return false when removing non-existing key", result);
    }
}
