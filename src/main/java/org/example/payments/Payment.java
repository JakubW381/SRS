package org.example.payments;

import org.example.model.User;

public interface Payment {
    String processPayment(double total, User user);
    void refund(double total, User user);
}
