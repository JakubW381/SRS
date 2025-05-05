package org.example.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class EntryLog {

    private UUID id;
    private User user;
    private String entryTime;
    private String exitTime;

    public EntryLog(User user, String entryTime) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.entryTime = entryTime;
        this.exitTime = "";
    }
    public void registerExit(){
        setExitTime(LocalDateTime.now().toString());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }


}
