package main;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainAppTest {
    
    @Test
    public void testMain_CallsMethod_NoExceptions() {
        try {
            String[] args = new String[0];
            MainApp.main(args);
            
            assertTrue(true);
        } catch (Exception e) {
            fail("Should not throw an exception: " + e.getMessage());
        }
    }
}
