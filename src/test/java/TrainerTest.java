import org.example.model.*;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {

    @Test
    public void testAssignToSession() {
        Trainer trainer = new Trainer("Kasia", Collections.singletonList("Fitness"));
        GroupSession session = new GroupSession(trainer, 10);

        trainer.assignToSession(session);
        trainer.assignToSession(session); // Drugi raz — powinno być ignorowane

        assertEquals(1, trainer.getAssignedSessions().size()); //dodac getter assignedSessions
    }

    @Test
    public void testTrainerEquality() {
        Trainer t1 = new Trainer("A", Collections.emptyList());
        Trainer t2 = new Trainer("A", Collections.emptyList());

        assertNotEquals(t1, t2);
    }
}
