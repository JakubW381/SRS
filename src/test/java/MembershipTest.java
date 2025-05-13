import org.example.model.Membership;
import org.example.model.enums.MEMBERSHIP_TYPE;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class MembershipTest {

    @Test
    public void testMonthlyMembership() {
        Membership membership = new Membership(MEMBERSHIP_TYPE.MONTHLY);

        assertEquals(30.0, membership.getPrice()); //dodac getter getPrice
        assertTrue(membership.isAcvite());
    }

    @Test
    public void testYearlyMembership() {
        Membership membership = new Membership(MEMBERSHIP_TYPE.YEARLY);

        assertEquals(0.8 * 365, membership.getPrice());
        assertTrue(membership.isAcvite());
    }

    @Test
    public void testCancelMembership() {
        Membership membership = new Membership(MEMBERSHIP_TYPE.MONTHLY);
        membership.cancel();

        assertFalse(membership.isAcvite());
    }

    @Test
    public void testExtendMembership() {
        Membership membership = new Membership(MEMBERSHIP_TYPE.MONTHLY);
        LocalDateTime originalDate = membership.getValidUntil(); //dodac getter getValidUntil

        membership.extendMembership(10);

        assertTrue(membership.getValidUntil().isAfter(originalDate));
    }
}
