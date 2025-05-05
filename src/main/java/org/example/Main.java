package org.example;


import org.example.model.User;
import org.example.model.enums.ROLE;
import org.example.payments.Payment;
import org.example.payments.impl.Blik;
import org.example.payments.impl.Card;
import org.example.payments.impl.Paypal;
import org.example.repo.UserRepo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserRepo userRepo = new UserRepo();
        User currentUser = null;

        Scanner scanner = new Scanner(System.in);
        String s = "";
        while(currentUser == null){
            System.out.println("login or register");
            s = scanner.nextLine();
            if (s.split(" ")[0].equals("login")) {
                System.out.println("login: ");
                String login = scanner.nextLine();
                System.out.println("password: ");
                String password = scanner.nextLine();
                currentUser = userRepo.login(login,password);
                break;
            }
            if (s.split(" ")[0].equals("register")) {
                System.out.println("login: ");
                String login = scanner.nextLine();
                System.out.println("password: ");
                String password = scanner.nextLine();
                System.out.println("email: ");
                String email = scanner.nextLine();
                currentUser = userRepo.register(login,password,email);
                break;
            }
            System.out.println("Auth Error, Try Again!");
        }
        if (currentUser != null){
            System.out.println("1. show user details");
            System.out.println("2. buy membership");
            System.out.println("3. show group sessions");
            System.out.println("4. add some cash");
            System.out.println("9. logout");
            if (currentUser.getRole().equals(ROLE.ADMIN)){
                System.out.println("5. add Trainer");
                System.out.println("6. add");
            }
            s = "";
            while(!s.split(" ")[0].equals("9")){
                s = scanner.nextLine();
                if (s.split(" ")[0].equals("1")){
                    System.out.println(currentUser);
                }
                if (s.split(" ")[0].equals("2")){
                    System.out.println("monthly:" + 1.0*30.0 + " or yearly: "+0.8*30.0+"?");
                    s = scanner.nextLine();
                    if (s.split(" ")[0].equals("monthly")){




                    }if (s.split(" ")[0].equals("yearly")){

                    }
                    else{
                        System.out.println("wrong value");
                    }
                }
                if (s.split(" ")[0].equals("3")){

                }
                if (s.split(" ")[0].equals("4")){
                    System.out.println("How much do you wanna add?");
                    double total = Integer.parseInt(scanner.nextLine());
                    System.out.println("How do you wanna pay?");
                    System.out.println("1.Blik?");
                    System.out.println("2.PayPal?");
                    System.out.println("3.Card?");
                    Payment paymentMethod = null;

                    if (s.split(" ")[0].toLowerCase().equals("blik")){
                        paymentMethod = new Blik();
                    }else if (s.split(" ")[0].toLowerCase().equals("paypal")){
                        paymentMethod = new Paypal();
                    }else if (s.split(" ")[0].toLowerCase().equals("card")){
                        paymentMethod = new Card();
                    }
                    if (paymentMethod != null){
                        paymentMethod.processPayment(total,currentUser);
                    }else{
                        System.out.println("Invalid Payment Option");
                    }
                }
            }
        }
    }
}