package monitoring;

import org.junit.Test;
import static org.junit.Assert.*;

public class RvdMonitorTest {
    
    @Test
    public void testStartMonitoring_CallsMethod_NoExceptions() {
        try {
            RvdMonitor.startMonitoring();
            
            assertTrue(true);
        } catch (Exception e) {
            fail("Should not throw an exception: " + e.getMessage());
        }
    }
}
