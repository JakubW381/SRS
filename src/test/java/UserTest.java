import org.example.model.*;
import org.example.model.enums.MEMBERSHIP_TYPE;
import org.example.model.enums.ROLE;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    public void testSetGetMembership(){
        Membership membership = new Membership(MEMBERSHIP_TYPE.YEARLY);
        User currentUser = new User(
                "name",
                "password",
                "email",
                ROLE.USER
        );
        currentUser.setMembership(membership);
        assertEquals(membership,currentUser.getMembreship());
    }

    @Test
    public void testCheckMembershipStatus(){
        Membership membership = new Membership(MEMBERSHIP_TYPE.YEARLY);
        User currentUser = new User(
                "name",
                "password",
                "email",
                ROLE.USER
        );
        assertEquals(false,currentUser.checkMembershipStatus());
        Membership expiredMembership = new Membership(MEMBERSHIP_TYPE.YEARLY);
        currentUser.setMembership(membership);
        assertEquals(true,currentUser.checkMembershipStatus());
        expiredMembership.cancel();
        currentUser.setMembership(expiredMembership);
        assertEquals(false,currentUser.checkMembershipStatus());
    }

    @Test
    public void testLogEntry(){
        User currentUser = new User(
                "name",
                "password",
                "email",
                ROLE.USER
        );

        assertEquals(currentUser.logEntry(),currentUser.getEntrylogs().getFirst());
        assertEquals(null,currentUser.getEntrylogs().getFirst().getExitTime());
    }
    @Test
    public void testLogExit(){
        User currentUser = new User(
                "name",
                "password",
                "email",
                ROLE.USER
        );
        assertEquals(false,currentUser.logExit());
        currentUser.logEntry();
        assertEquals(true,currentUser.logExit());
    }
    @Test
    public void testReserveSession(){
        User currentUser = new User(
                "name",
                "password",
                "email",
                ROLE.USER
        );
        Trainer trainer = new Trainer("Anna Kowalska", Arrays.asList("Yoga", "Pilates"));
        GroupSession session = new GroupSession(new Trainer("Anna Kowalska", Arrays.asList("Yoga", "Pilates")),5);

        currentUser.reserveSession(session);


    }
    @Test
    public void testCancelReservation(){
        User currentUser = new User(
                "name",
                "password",
                "email",
                ROLE.USER
        );
        Trainer trainer = new Trainer("Anna Kowalska", Arrays.asList("Yoga", "Pilates"));
        GroupSession session = new GroupSession(new Trainer("Anna Kowalska", Arrays.asList("Yoga", "Pilates")),5);
        assertThrows(NoSuchElementException.class,() -> currentUser.cancelReservation(session));

    }

}
