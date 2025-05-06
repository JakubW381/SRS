package org.example.model;

import org.example.model.enums.MEMBERSHIP_TYPE;

import java.time.LocalDateTime;
import java.util.UUID;

public class Membership {

    private UUID id;
    private MEMBERSHIP_TYPE type;
    private LocalDateTime validUntil;
    private double price;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Membership Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Valid Until: ").append(validUntil).append("\n");
        sb.append("Price: ").append(price).append("\n");
        return sb.toString();
    }

    public Membership(MEMBERSHIP_TYPE type) {
        this.type = type;
        this.id = UUID.randomUUID();
            if (type.equals(MEMBERSHIP_TYPE.MONTHLY)){
                this.price = 1.0*30;
                this.validUntil = LocalDateTime.now().plusDays(30);
            }else{
                this.price = 0.8*365;
                this.validUntil = LocalDateTime.now().plusDays(365);
            }
    }

    public boolean isAcvite(){
        return LocalDateTime.now().isBefore(validUntil);
    }

    public void extendMembership(int days) {
        if (days > 0) {
            this.validUntil = this.validUntil.plusDays(days);
            System.out.println("Membership extended by " + days + " days.");
        } else {
            System.out.println("Invalid number of days.");
        }
    }
    public void cancel() {
        this.validUntil = LocalDateTime.now().minusDays(1);
        System.out.println("Membership has been canceled.");
    }

}
