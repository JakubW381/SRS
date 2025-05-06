package org.example.repo;

import org.example.model.enums.ROLE;
import org.example.model.User;
import java.util.List;

public class UserRepo {

    List<User> userDB;
    public UserRepo() {
        User user = new User("Maciej","haslo1", "maciej@maciej.pl" , ROLE.ADMIN);
        userDB.add(user);
        User user2 = new User("Adrian","haslo2", "adrian@adrian.pl" , ROLE.USER);
        userDB.add(user2);
    }
    public List<User> findAll(){
        return userDB;
    }
    public void save(List<User> updated){
        userDB = updated;
    }

    public User register(String username, String password, String email){
        boolean able = true;
        for (User user : userDB){
            if (user.getName().equals(username)){
                able = false;
                break;
            }
        }
        if (able){
            User user = new User(
                    username,
                    password,
                    email,
                    ROLE.USER
            );
            userDB.add(user);
            return user;
        }else{
            System.out.println("User already exists");
            return null;
        }
    }
    public User login(String username, String password){
        for (User user : userDB){
            if (user.getName().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        System.out.println("Wrong credentials");
        return null;
    }
}
