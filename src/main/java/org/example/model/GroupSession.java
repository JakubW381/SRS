package org.example.model;

import java.util.List;
import java.util.Optional;
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
        this.participants = List.of();
    }

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public String toString() {
        return "GroupSession{" +
                "id=" + id +
                ", trainer=" + trainer +
                ", participants=" + participants.size()+"/"+capacity +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public boolean addParticipant(User user){
        if(participants.contains(user)){
            System.out.println("Your are already in that session");
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
        Optional<User> user = participants.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        participants.remove(user);
    }



}
