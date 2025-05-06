package org.example.model;

import java.util.List;
import java.util.UUID;

public class Trainer {
    private UUID id;
    private String name;
    private List<String> specialities;
    private List<GroupSession> assignedSessions;

    public Trainer(String name, List<String> specialities) {
        this.name = name;
        this.specialities = specialities;
        this.id = UUID.randomUUID();
        this.assignedSessions = List.of();
    }

    public UUID getId() {
        return id;
    }

    public void assignToSession(GroupSession session){
        if (!assignedSessions.contains(session)){
            assignedSessions.add(session);
        }
        else{
            System.out.println("Already assigned to that session");
        }
    }



}
