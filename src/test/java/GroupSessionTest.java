import org.example.model.*;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GroupSessionTest {

    @Test
    public void testAddParticipant() {
        Trainer trainer = new Trainer("Anna", Collections.singletonList("Yoga"));
        GroupSession session = new GroupSession(trainer, 2);
        User user1 = new User("User1", "pass", "user1@example.com", org.example.model.enums.ROLE.USER);

        boolean added = session.addParticipant(user1);

        assertTrue(added);
        assertFalse(session.isFull());
    }

    @Test
    public void testAddDuplicateParticipant() {
        Trainer trainer = new Trainer("Anna", Collections.singletonList("Yoga"));
        GroupSession session = new GroupSession(trainer, 2);
        User user = new User("User", "pass", "user@example.com", org.example.model.enums.ROLE.USER);

        session.addParticipant(user);
        boolean addedAgain = session.addParticipant(user);

        assertFalse(addedAgain);
    }

    @Test
    public void testSessionFull() {
        Trainer trainer = new Trainer("Anna", Collections.singletonList("Yoga"));
        GroupSession session = new GroupSession(trainer, 1);
        User user1 = new User("User1", "pass", "u1@example.com", org.example.model.enums.ROLE.USER);
        User user2 = new User("User2", "pass", "u2@example.com", org.example.model.enums.ROLE.USER);

        assertTrue(session.addParticipant(user1));
        assertFalse(session.addParticipant(user2));
    }
}
