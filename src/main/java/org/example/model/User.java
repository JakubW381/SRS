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
    private Membership membreship;
    private List<EntryLog> entrylogs;
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", membreship=" + membreship +
                ", entrylogs=" + entrylogs +
                ", reservations=" + reservations +
                ", role=" + role +
                '}';
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMembreship(Membership membreship) {
        this.membreship = membreship;
    }

    public void setEntrylogs(List<EntryLog> entrylogs) {
        this.entrylogs = entrylogs;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Membership getMembreship() {
        return membreship;
    }

    public List<EntryLog> getEntrylogs() {
        return entrylogs;
    }

    public List<Reservation> getReservations() {
        return reservations;
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
          this,
                LocalDateTime.now().toString()
        );
        this.entrylogs.add(log);
        return log;
    }
    public void logExit(String exitTime) {
        EntryLog log = this.entrylogs.getLast();
        log.setExitTime(exitTime);
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
