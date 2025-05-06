package org.example;

import org.example.model.*;
import org.example.model.enums.MEMBERSHIP_TYPE;
import org.example.model.enums.ROLE;
import org.example.payments.Payment;
import org.example.payments.impl.Blik;
import org.example.payments.impl.Card;
import org.example.payments.impl.Paypal;
import org.example.repo.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        UserRepo userRepo = new UserRepo();
        GroupSessionRepo groupSessionRepo = new GroupSessionRepo();
        TrainerRepo trainerRepo = new TrainerRepo();

        User currentUser;
        Scanner scanner = new Scanner(System.in);
        String s = "";
        while(s.split(" ")[0].equals("EXIT")){

            while(true){//--------------- Autentykacja
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

            if (currentUser != null){//--------------------- "UI" dla zalogowanego usera i admina
                s = "";
                while(!s.split(" ")[0].equals("0")){
                    System.out.println("1. show user details");
                    System.out.println("2. buy membership");
                    System.out.println("3. show group sessions");
                    System.out.println("4. add some cash");
                    System.out.println("0. logout");
                    if (currentUser.getRole().equals(ROLE.ADMIN)){
                        System.out.println("5. add Trainer");
                        System.out.println("6. add Trainer");
                        System.out.println("7. add Group Session");
                        System.out.println("8. delete Group Session");
                    }
                    s = scanner.nextLine();

                    //---------------------USER COMMANDS

                    if (s.split(" ")[0].equals("1")){ //---------------------Show me
                        System.out.println(currentUser);
                    }
                    if (s.split(" ")[0].equals("2")){ //------------------------------------------Buy Membership
                        System.out.println("monthly:" + 1.0*30.0 + " or yearly: "+0.8*30.0+"?");
                        s = scanner.nextLine();
                        if (s.split(" ")[0].equalsIgnoreCase("monthly")){//---------------------monthly
                            double total = 1.0*30.0;
                            if (currentUser.getWallet()<total){
                                System.out.println("Insufficient funds");
                            }else{
                                System.out.println("How do you wanna pay?");
                                System.out.println("1.Blik?");
                                System.out.println("2.PayPal?");
                                System.out.println("3.Card?");
                                s = scanner.nextLine();
                                Payment paymentMethod = null;

                                if (s.split(" ")[0].toLowerCase().equals("blik")){
                                    paymentMethod = new Blik();
                                }else if (s.split(" ")[0].toLowerCase().equals("paypal")){
                                    paymentMethod = new Paypal();
                                }else if (s.split(" ")[0].toLowerCase().equals("card")){
                                    paymentMethod = new Card();
                                }
                                if (paymentMethod != null){
                                    System.out.println(paymentMethod.processPayment(total,currentUser));
                                    currentUser.setMembreship(
                                            new Membership(MEMBERSHIP_TYPE.MONTHLY)
                                    );
                                }else{
                                    System.out.println("Invalid Payment Option");
                                }
                            }
                        }if (s.split(" ")[0].equalsIgnoreCase("yearly")){//---------------------yearly
                            double total = 0.8*30.0;
                            if (currentUser.getWallet()<total){
                                System.out.println("Insufficient funds");
                            }else{
                                System.out.println("How do you wanna pay?");
                                System.out.println("1.Blik?");
                                System.out.println("2.PayPal?");
                                System.out.println("3.Card?");
                                s = scanner.nextLine();
                                Payment paymentMethod = null;

                                if (s.split(" ")[0].toLowerCase().equals("blik")){
                                    paymentMethod = new Blik();
                                }else if (s.split(" ")[0].toLowerCase().equals("paypal")){
                                    paymentMethod = new Paypal();
                                }else if (s.split(" ")[0].toLowerCase().equals("card")){
                                    paymentMethod = new Card();
                                }
                                if (paymentMethod != null){
                                    System.out.println(paymentMethod.processPayment(total,currentUser));
                                    currentUser.setMembreship(
                                            new Membership(MEMBERSHIP_TYPE.YEARLY)
                                    );
                                }else{
                                    System.out.println("Invalid Payment Option");
                                }
                            }
                        }
                        else{
                            System.out.println("wrong value");
                        }
                    }
                    if (s.split(" ")[0].equals("3")){ //--------------------Show Group Sessions with reservation/cancel option
                        for (GroupSession session : groupSessionRepo.findAll()){
                            System.out.println(session.toString());
                        }
                        System.out.println("1. Join Group Session");
                        System.out.println("2. Cancel your Reservation");
                        System.out.println("3. Back");
                        s = scanner.nextLine();
                        if (s.split(" ")[0].equals("1")){//---------------------reservation
                            if (currentUser.checkMembershipStatus()){
                                System.out.println("Enter session ID:");
                                UUID id = UUID.fromString(scanner.nextLine());
                                Optional<GroupSession> sessionOpt = groupSessionRepo.findById(id);
                                if (sessionOpt.isPresent()){
                                    GroupSession session = sessionOpt.get();
                                    session.addParticipant(currentUser);
                                    currentUser.reserveSession(session);
                                    groupSessionRepo.save(session);
                                }else{
                                    System.out.println("Wrong session ID");
                                }
                            }else{
                                System.out.println("Membership Inactive");
                            }
                        }
                        if (s.split(" ")[0].equals("2")){//---------------------cancel
                            System.out.println("Enter session ID:");
                            UUID id = UUID.fromString(scanner.nextLine());
                            Optional<GroupSession> sessionOpt = groupSessionRepo.findById(id);
                            if (sessionOpt.isPresent()){
                                GroupSession session = sessionOpt.get();
                                currentUser.cancelReservation(session);
                                session.removeParticipant(currentUser.getId());
                                groupSessionRepo.save(session);
                            }else{
                                System.out.println("Wrong session ID");
                            }
                        }
                        s = "";
                    }
                    if (s.split(" ")[0].equals("4")){  //---------------------Add cash to "wallet"

//                    To tak jakby prawdziwy portfel użytkownika
//                    którego na niby używa do kupowania członkostwa

                        System.out.println("How much do you wanna add?");
                        double total = Integer.parseInt(scanner.nextLine());
                        currentUser.setWallet(currentUser.getWallet() + total);
                    }
                    if(s.split(" ")[0].equals("5")){ //---------show Trainers
                        for(Trainer trainer : trainerRepo.findAll()){
                            System.out.println(trainer.toString());
                        }
                    }

                    //------------------ADMIN COMMANDS

                    if (currentUser.getRole().equals(ROLE.ADMIN)){

                        if(s.split(" ")[0].equals("6")){ //---------Add Trainer
                            System.out.println("Enter trainer name");
                            String name = scanner.nextLine();

                            System.out.println("Enter trainer's specialities separated with ',' ");
                            List<String> specialities = Arrays.stream(scanner.nextLine().split(",")).toList();

                            Trainer trainer = new Trainer(
                                    name,
                                    specialities
                            );
                        }
                        if(s.split(" ")[0].equals("7")){ //---------Delete Trainer
                            System.out.println("Enter trainer ID");
                            UUID id = UUID.fromString(scanner.nextLine());

                            if (groupSessionRepo.findByTrainerId(id).isEmpty()){
                                trainerRepo.removeTrainer(id);
                            }else{
                                System.out.println("First delete this trainer from their Group Sessions");
                            }
                        }
                        if(s.split(" ")[0].equals("8")){ //---------Add Group Session
                            System.out.println("Enter trainer ID");
                            UUID id = UUID.fromString(scanner.nextLine());
                            if (trainerRepo.findById(id).isPresent()){
                                int cap = Integer.parseInt(scanner.nextLine());
                                GroupSession session = new GroupSession(
                                        trainerRepo.findById(id).get(),
                                        cap
                                );
                            }else{
                                System.out.println("Invalid Trainer Id");
                            }
                        }
                        if(s.split(" ")[0].equals("9")){ //---------Delete Group Session
                            System.out.println("Enter Group Session ID");
                            UUID id = UUID.fromString(scanner.nextLine());
                            Optional<GroupSession> groupSessionOpt = groupSessionRepo.findById(id);
                            if (groupSessionOpt.isPresent()){
                                GroupSession session = groupSessionOpt.get();
                                for (User user : userRepo.findAll()){
                                    if (
                                            user.getReservations().stream()
                                                .anyMatch(r -> r.getGroupSession().equals(session))
                                    ){
                                        user.cancelReservation(session);
                                    }
                                }
                                groupSessionRepo.deleteSession(id);
                            }else{
                                System.out.println("Invalid Session Id");
                            }
                        }
                    }
                }
                currentUser.logExit();
                currentUser = null;
            }
        }
    }
}