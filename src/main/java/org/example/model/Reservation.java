package org.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private UUID id;
    private User user;
    private GroupSession groupSession;
    private LocalDateTime reservationDate;

    public Reservation(User user, GroupSession groupSession) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.groupSession = groupSession;
        this.reservationDate = LocalDateTime.now();
    }

    public GroupSession getGroupSession() {
        return groupSession;
    }
//    public void cancelReservation(){
//
//    }
//    public void confirmReservation(){
//
//    }
}
