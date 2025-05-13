import org.example.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class EntryLogTest {

    @Test
    public void testEntryLogCreation() {
        User user = new User("Jan", "pass123", "jan@example.com", org.example.model.enums.ROLE.USER);
        EntryLog log = new EntryLog(user);

        assertNotNull(log.getEntryTime());
        assertNull(log.getExitTime());
        assertEquals(user, log.getUser());
    }

    @Test
    public void testExitTimeSet() {
        User user = new User("Jan", "pass123", "jan@example.com", org.example.model.enums.ROLE.USER);
        EntryLog log = new EntryLog(user);
        log.setExitTime();

        assertNotNull(log.getExitTime());
        assertTrue(log.getExitTime().isAfter(log.getEntryTime()) || log.getExitTime().isEqual(log.getEntryTime()));
    }
}
