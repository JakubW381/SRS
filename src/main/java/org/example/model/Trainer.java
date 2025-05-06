package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Trainer {
    private UUID id;
    private String name;
    private List<String> specialities;
    private List<GroupSession> assignedSessions;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trainer Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Specialties: ").append(specialities != null && !specialities.isEmpty() ? String.join(", ", specialities) : "No specialties listed").append("\n");
        if (assignedSessions != null && !assignedSessions.isEmpty()) {
            sb.append("Assigned Sessions: ").append(assignedSessions.size()).append("\n");
            for (GroupSession session : assignedSessions) {
                sb.append("\t").append(session.toString()).append("\n");
            }
        } else {
            sb.append("Assigned Sessions: No sessions assigned\n");
        }

        return sb.toString();
    }


    public Trainer(String name, List<String> specialities) {
        this.name = name;
        this.specialities = specialities;
        this.id = UUID.randomUUID();
        this.assignedSessions = new ArrayList<>();
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id.equals(trainer.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
