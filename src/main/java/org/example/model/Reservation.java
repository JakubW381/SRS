package org.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private UUID id;
    private User user;
    private GroupSession groupSession;
    private LocalDateTime reservationDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("User: ").append(user.getName()).append("\n");
        sb.append("Group Session: ").append(groupSession != null ? groupSession.toString() : "No Session").append("\n");
        sb.append("Reservation Date: ").append(reservationDate).append("\n");
        return sb.toString();
    }

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
