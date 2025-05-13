import org.example.model.*;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    public void testReservationCreation() {
        Trainer trainer = new Trainer("Trainer1", Collections.singletonList("Pilates"));
        GroupSession session = new GroupSession(trainer, 10);
        User user = new User("User", "pass", "email@example.com", org.example.model.enums.ROLE.USER);
        Reservation reservation = new Reservation(user, session);

        assertNotNull(reservation.getGroupSession());
        assertEquals(session, reservation.getGroupSession());
    }
}
