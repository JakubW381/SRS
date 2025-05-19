package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupSession {
    private UUID id;
    private Trainer trainer;
    private int capacity;
    private List<User> participants;

    public GroupSession(Trainer trainer, int capacity) {
        this.id = UUID.randomUUID();
        this.trainer = trainer;
        this.capacity = capacity;
        this.participants = new ArrayList<>();
    }

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group Session Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append(trainer != null ? trainer.toString() : "Trainer: No Trainer").append("\n");
        sb.append("Participants | Capacity: ").append(participants != null ? participants.size() : 0).append(" / ").append(capacity).append("\n");
        return sb.toString();
    }


    public UUID getId() {
        return id;
    }

    public boolean addParticipant(User user){
        if(participants.contains(user)){
            System.out.println("You are already in that session");
            return false;
        }
        if (!isFull()){
            participants.add(user);
            return true;
        }else{
            System.out.println("Session is full");
            return false;
        }
    }
    public boolean isFull(){
        return participants.size() >= capacity;
    }
    public void removeParticipant(UUID id){
        participants.removeIf(u -> u.getId().equals(id));
    }
}
