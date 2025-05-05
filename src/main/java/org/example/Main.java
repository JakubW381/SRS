package org.example;


import org.example.model.User;
import org.example.repo.UserRepo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserRepo userRepo = new UserRepo();
        User currentUser = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("login or register");
        String s = scanner.nextLine();
        if (s.split(" ")[0].equals("login")) {
            System.out.println("login: ");
            String login = scanner.nextLine();
            System.out.println("password: ");
            String password = scanner.nextLine();
            currentUser = userRepo.login(login,password);
        }
        if (s.split(" ")[0].equals("register")) {
            System.out.println("login: ");
            String login = scanner.nextLine();
            System.out.println("password: ");
            String password = scanner.nextLine();
            System.out.println("email: ");
            String email = scanner.nextLine();
            currentUser = userRepo.register(login,password,email);
        }
        if (currentUser != null){
            System.out.println("1. show user details");
            System.out.println("2. buy membership");
            System.out.println("3. reserve group session");
            System.out.println("4. logout");
            s = "";
            while(!s.split(" ")[0].equals("4")){
                s = scanner.nextLine();
                if (s.split(" ")[0].equals("1")){
                    System.out.println(currentUser.toString());
                }
                if (s.split(" ")[0].equals("2")){

                }
                if (s.split(" ")[0].equals("3")){

                }
            }
        }
    }
}