package org.example.model;

import org.example.model.enums.MEMBERSHIP_TYPE;

import java.time.LocalDateTime;
import java.util.UUID;

public class Membership {

    private UUID id;
    private MEMBERSHIP_TYPE type;
    private LocalDateTime validUntil;
    private double price;

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
    public void extendMembership(int days){

    }
    public void cancel(){

    }

}
