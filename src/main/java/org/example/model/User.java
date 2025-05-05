package org.example.model;

import org.example.model.enums.ROLE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    public User(String name,String password, String email , ROLE role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.membreship = null;
        this.entrylogs = List.of();
        this.reservations = List.of();
        this.role = role;
    }

    public Reservation reserveSession(GroupSession session) {
        return new Reservation();
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
