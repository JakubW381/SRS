package org.example.model;

import org.example.model.enums.ROLE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private double wallet;
    private Membership membership;
    private List<EntryLog> entrylogs;
    private List<Reservation> reservations;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Wallet Balance: ").append(wallet).append("\n");
        sb.append("Membership: ").append(membership != null ? membership.toString() : "No Membership").append("\n");
        if (entrylogs != null && !entrylogs.isEmpty()) {
            sb.append("Entry Logs: ").append(entrylogs.size()).append(" entries\n");
            for (EntryLog log : entrylogs) {
                sb.append("\t").append(log.toString()).append("\n");
            }
        } else {
            sb.append("Entry Logs: No entries\n");
        }
        if (reservations != null && !reservations.isEmpty()) {
            sb.append("Reservations: ").append(reservations.size()).append(" reservations\n");
            for (Reservation reservation : reservations) {
                sb.append("\t").append(reservation.toString()).append("\n");
            }
        } else {
            sb.append("Reservations: No reservations\n");
        }
        return sb.toString();
    }


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
        return membership;
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
        this.membership = membreship;
    }

    public User(String name, String password, String email , ROLE role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.membership = null;
        this.entrylogs = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.role = role;
    }

    public void reserveSession(GroupSession session) {
        reservations.add(new Reservation(this,session));
    }
    public void cancelReservation(GroupSession session) {
        Optional<Reservation> reservationOpt = reservations.stream()
                .filter(r -> r.getGroupSession().getId().equals(session.getId()))
                .findFirst();
        reservationOpt.ifPresent(reservation -> reservations.remove(reservation));
    }
    public EntryLog logEntry() {
        EntryLog log = new EntryLog(
          this
        );
        this.entrylogs.add(log);
        return log;
    }
    public void logExit() {
        if (!entrylogs.isEmpty()) {
            EntryLog log = entrylogs.get(entrylogs.size() - 1);
            log.setExitTime();
            this.entrylogs.set(entrylogs.size() - 1, log);
        } else {
            System.out.println("No entry log found for this user.");
        }
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
