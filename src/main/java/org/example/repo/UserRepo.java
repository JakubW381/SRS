package org.example.repo;

import org.example.model.enums.ROLE;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    private List<User> userDB = new ArrayList<>();

    public UserRepo() {
        userDB.add(new User("Maciej", "haslo1", "maciej@maciej.pl", ROLE.ADMIN));
        userDB.add(new User("Adrian", "haslo2", "adrian@adrian.pl", ROLE.USER));
        userDB.add(new User("Kasia", "haslo3", "kasia@kasia.pl", ROLE.USER));
        userDB.add(new User("Tomek", "haslo4", "tomek@tomek.pl", ROLE.USER));
        userDB.add(new User("Basia", "haslo5", "basia@basia.pl", ROLE.USER));
        userDB.add(new User("Grzegorz", "haslo6", "grzegorz@grzegorz.pl", ROLE.USER));
        userDB.add(new User("Ola", "haslo7", "ola@ola.pl", ROLE.USER));
    }
    public List<User> findAll(){
        return userDB;
    }
    public void save(List<User> updated){
        userDB = updated;
    }

    public User register(String username, String password, String email) {
        boolean exists = userDB.stream().anyMatch(user -> user.getName().equals(username));
        if (exists) {
            System.out.println("User already exists");
            return null;
        }
        User user = new User(username, password, email, ROLE.USER);
        userDB.add(user);
        return user;
    }

    public User login(String username, String password) {
        return userDB.stream()
                .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Wrong credentials");
                    return null;
                });
    }

}
