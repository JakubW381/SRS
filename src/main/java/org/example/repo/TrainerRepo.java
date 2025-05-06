package org.example.repo;

import org.example.model.Trainer;

import java.util.*;

public class TrainerRepo {

    private List<Trainer> trainerDB;

    public TrainerRepo() {
        this.trainerDB = new ArrayList<>(List.of(
                new Trainer("Anna Kowalska", Arrays.asList("Yoga", "Pilates")),
                new Trainer("Marek Nowak", Arrays.asList("Crossfit", "TRX")),
                new Trainer("Ewa Wiśniewska", Arrays.asList("Zumba", "Cardio")),
                new Trainer("Krzysztof Krawczyk", Arrays.asList("Bodybuilding", "Strength")),
                new Trainer("Joanna Majewska", Arrays.asList("Dance", "Stretching")),
                new Trainer("Tomasz Zieliński", Arrays.asList("Running", "Cycling")),
                new Trainer("Agnieszka Wójcik", Arrays.asList("Boxing", "Kickboxing")),
                new Trainer("Piotr Jankowski", Arrays.asList("Swimming", "Aqua Aerobics")),
                new Trainer("Katarzyna Kamińska", Arrays.asList("Yoga", "Meditation")),
                new Trainer("Łukasz Piątek", Arrays.asList("Calisthenics", "Parkour"))
        ));
    }

    public List<Trainer> findAll(){
        return trainerDB;
    }
    public Optional<Trainer> findById(UUID id){
        return trainerDB.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst();
    }
    public void addTrainer(Trainer trainer){
        if (!trainerDB.contains(trainer)){
            trainerDB.add(trainer);
        }else {
            System.out.println("Trainer already exists");
        }
    }
    public void removeTrainer(UUID id){
        Optional<Trainer> trainerOpt = trainerDB.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        if (trainerOpt.isPresent()){
            trainerDB.remove(trainerOpt.get());
        }
        else{
            System.out.println("Trainer doesn't exist");
        }
    }
}
