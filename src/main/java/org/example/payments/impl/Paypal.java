package org.example.payments.impl;

import org.example.model.User;
import org.example.payments.Payment;

public class Paypal implements Payment {

    @Override
    public String processPayment(double total, User user) {
        user.setWallet(user.getWallet() - total);
        return "Paid " + total + " using Paypal.";
    }

    @Override
    public void refund(double total, User user) {
        user.setWallet(user.getWallet() + total);
        System.out.println("Membership refunded for: " + total + " using Paypal.");
    }
}
