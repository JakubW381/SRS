package org.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class EntryLog {

    private UUID id;
    private User user;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public EntryLog(User user) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entry Log Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("User: ").append(user != null ? user.getName() : "No User").append("\n");
        sb.append("Entry Time: ").append(entryTime).append("\n");
        sb.append("Exit Time: ").append(exitTime != null ? exitTime : "Not exited yet").append("\n");
        return sb.toString();
    }

//    public void registerExit(){
//        this.exitTime = LocalDateTime.now();
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }


}
