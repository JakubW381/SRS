package org.example.model;

import org.example.model.enums.MEMBERSHIP_TYPE;

import java.time.LocalDateTime;
import java.util.UUID;

public class Membership {

    private UUID id;
    private MEMBERSHIP_TYPE type;
    private String validUntil;
    private double price;

    public Membership(MEMBERSHIP_TYPE type) {
        this.type = type;
        this.id = UUID.randomUUID();
            if (type.equals(MEMBERSHIP_TYPE.MONTHLY)){
                this.price = 50.00;
                LocalDateTime.now();
                //TODO


                this.validUntil = ;
            }else{
                this.price = 500.00;
                this.validUntil = validUntil;
            }



    }

    public boolean isAcvite(){


        return true;
    }
    public void extendMembership(int days){

    }
    public void cancel(){

    }

}
