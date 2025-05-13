import org.example.model.User;
import org.example.model.enums.ROLE;
import org.example.payments.impl.Blik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlikTest {

    private Blik blik;
    private User user;

    @BeforeEach
    void setUp() {
        blik = new Blik();
        user = new User("TestUser", "pass", "test@example.com", ROLE.USER);
        user.setWallet(100.0);
    }

    @Test
    void testProcessPaymentWithSufficientFunds() {
        double amount = 60.0;
        String result = blik.processPayment(amount, user);

        assertEquals(40.0, user.getWallet(), 0.01);
        assertEquals("Paid 60.0 using Blik.", result);
    }

    @Test
    void testProcessPaymentWithInsufficientFunds() {
        double amount = 150.0;
        String result = blik.processPayment(amount, user);

        assertEquals(100.0, user.getWallet(), 0.01); // Wallet unchanged
        assertEquals("Insufficient funds.", result);
//        if (user.getWallet() < total) {
//            return "Insufficient funds.";
//        }
    }

    @Test
    void testRefund() {
        double amount = 30.0;
        blik.refund(amount, user);

        assertEquals(130.0, user.getWallet(), 0.01);
    }
}