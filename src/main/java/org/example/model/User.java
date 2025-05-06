package org.example.model;

import org.example.model.enums.ROLE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private double wallet;
    private Membership membreship;
    private List<EntryLog> entrylogs;
    private List<Reservation> reservations;

    public String getName() {
        return name;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getPassword() {
        return password;
    }

    public Membership getMembreship() {
        return membreship;
    }

    public ROLE getRole() {
        return role;
    }

    private ROLE role;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public UUID getId() {
        return id;
    }

    public void setMembreship(Membership membreship) {
        this.membreship = membreship;
    }

    public User(String name, String password, String email , ROLE role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.membreship = null;
        this.entrylogs = List.of();
        this.reservations = List.of();
        this.role = role;
    }

    public void reserveSession(GroupSession session) {
        reservations.add(new Reservation(this,session));
    }
    public void cancelReservation(GroupSession session){
        Optional<Reservation> reservationOpt = reservations.stream()
                .filter(r -> r.getGroupSession().equals(session))
                .findFirst();
        if (reservationOpt.isPresent()){
            reservations.remove(reservationOpt.get());
        }else{
            System.out.println("No such reservation");
        }
    }
    public EntryLog logEntry() {
        EntryLog log = new EntryLog(
          this
        );
        this.entrylogs.add(log);
        return log;
    }
    public void logExit() {
        EntryLog log = this.entrylogs.getLast();
        log.setExitTime();
        this.entrylogs.removeLast();
        this.entrylogs.add(log);
    }
    public boolean checkMembershipStatus() {
        if (getMembreship() == null){
            return false;
        }
        if (getMembreship().isAcvite()){
            return true;
        }
        return false;
    }
}
