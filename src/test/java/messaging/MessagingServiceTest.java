package messaging;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessagingServiceTest {
    
    @Test
    public void testStart_CallsMethod_NoExceptions() {
        try {
            MessagingService.start();
            
            assertTrue(true);
        } catch (Exception e) {
            fail("Should not throw an exception: " + e.getMessage());
        }
    }
}
