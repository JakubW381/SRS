import org.example.model.User;
import org.example.model.enums.ROLE;
import org.example.payments.impl.Paypal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaypalTest {

    private Paypal paypal;
    private User user;

    @BeforeEach
    void setUp() {
        paypal = new Paypal();
        user = new User("TestUser", "pass", "test@example.com", ROLE.USER);
        user.setWallet(100.0);
    }

    @Test
    void testProcessPaymentWithSufficientFunds() {
        double amount = 60.0;
        String result = paypal.processPayment(amount, user);

        assertEquals(40.0, user.getWallet(), 0.01);
        assertEquals("Paid 60.0 using Paypal.", result);
    }

    @Test
    void testProcessPaymentWithInsufficientFunds() {
        double amount = 150.0;
        String result = paypal.processPayment(amount, user);

        assertEquals(100.0, user.getWallet(), 0.01);
        assertEquals("Insufficient Funds", result);
    }

    @Test
    void testRefund() {
        double amount = 30.0;
        paypal.refund(amount, user);

        assertEquals(130.0, user.getWallet(), 0.01);
    }
}