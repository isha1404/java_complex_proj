package launcher;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProcessLauncherTest {
    
    @Test
    public void testLaunchProcess_CallsMethod_NoExceptions() {
        try {
            ProcessLauncher.launchProcess();
            
            assertTrue(true);
        } catch (Exception e) {
            fail("Should not throw an exception: " + e.getMessage());
        }
    }
}
