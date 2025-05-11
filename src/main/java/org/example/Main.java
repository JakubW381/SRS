package org.example;

import org.example.model.*;
import org.example.model.enums.MEMBERSHIP_TYPE;
import org.example.model.enums.ROLE;
import org.example.payments.Payment;
import org.example.payments.impl.Blik;
import org.example.payments.impl.Card;
import org.example.payments.impl.Paypal;
import org.example.repo.*;
import org.example.ui.MenuUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        UserRepo userRepo = new UserRepo();
        GroupSessionRepo groupSessionRepo = new GroupSessionRepo();
        TrainerRepo trainerRepo = new TrainerRepo();

        Scanner scanner = new Scanner(System.in);
        String s = "";
        while(true){
            User currentUser = null;
            // Authentication menu
            int authChoice;
            do {
                List<String> authOptions = Arrays.asList("[1] Login", "[2] Register", "[3] Exit");
                MenuUtils.displayMenu("Authentication Menu", authOptions, currentUser);
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    scanner.next();
                }
                authChoice = scanner.nextInt();
                switch (authChoice) {
                    case 1: // Login
                        scanner.nextLine(); // For login/password feed spacing
                        System.out.print("Login: ");
                        String login = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        currentUser = userRepo.login(login, password);
                        if (currentUser != null) currentUser.logEntry();
                        else break;
                        break;
                    case 2: // Register
                        scanner.nextLine(); // For login/password feed spacing
                        System.out.print("Login: ");
                        String regLogin = scanner.nextLine();
                        System.out.print("Password: ");
                        String regPassword = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        currentUser = userRepo.register(regLogin, regPassword, email);
                        if (currentUser != null) currentUser.logEntry();
                        else break;
                        break;
                    case 3: // Exit
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Auth Error, Try Again!");
                        continue;
                }
                if (authChoice == 1 || authChoice == 2) break;
            } while (true);

            // "UI" dla zalogowanego usera i admina
            if (currentUser != null) {
                int userChoice = -1;
                do {
                    List<String> userOptions = new ArrayList<>(Arrays.asList(
                        "[1] Show user details",
                        "[2] Buy membership",
                        "[3] Show group sessions",
                        "[4] Add some cash",
                        "[5] Show trainers",
                        "[6] Logout"
                    ));
                    if (currentUser.getRole().equals(ROLE.ADMIN)){
                        userOptions.addAll(Arrays.asList(
                            "[7] Add Trainer",
                            "[8] Delete Trainer",
                            "[9] Add Group Session",
                            "[10] Delete Group Session"
                        ));
                    }
                    MenuUtils.displayMenu("Main Menu", userOptions, currentUser);
                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        scanner.next();
                    }
                    userChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    switch (userChoice) {
                        case 1: // Show user details
                            System.out.println(currentUser);
                            break;
                        case 2: // Buy membership
                            System.out.println("1. Monthly: " + 1.0 * 30.0);
                            System.out.println("2. Yearly: " + 0.8 * 365.0);
                            System.out.print("Select membership type: ");
                            int membChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (membChoice != 1 && membChoice != 2) {
                                System.out.println("Invalid membership type");
                                break;
                            }
                            double total = (membChoice == 1) ? 1.0 * 30.0 : 0.8 * 365.0;
                            if (currentUser.getWallet() < total) {
                                System.out.println("Insufficient funds");
                                break;
                            }
                            System.out.println("How do you wanna pay?");
                            System.out.println("1. Blik");
                            System.out.println("2. PayPal");
                            System.out.println("3. Card");
                            System.out.print("Select payment method: ");
                            int payChoice = scanner.nextInt();
                            scanner.nextLine();
                            Payment paymentMethod = null;
                            switch (payChoice) {
                                case 1: paymentMethod = new Blik(); break;
                                case 2: paymentMethod = new Paypal(); break;
                                case 3: paymentMethod = new Card(); break;
                                default: System.out.println("Invalid Payment Option"); break;
                            }
                            if (paymentMethod != null) {
                                System.out.println(paymentMethod.processPayment(total, currentUser));
                                currentUser.setMembreship(
                                    new Membership(membChoice == 1 ? MEMBERSHIP_TYPE.MONTHLY : MEMBERSHIP_TYPE.YEARLY)
                                );
                            }
                            break;
                        case 3: // Show group sessions
                            for (GroupSession session : groupSessionRepo.findAll()) {
                                System.out.println(session.toString());
                            }
                            System.out.println("[1] Join Group Session");
                            System.out.println("[2] Cancel your Reservation");
                            System.out.println("[3] Back");
                            System.out.print("Select option: ");
                            int groupChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (groupChoice) {
                                case 1:
                                    if (currentUser.checkMembershipStatus()) {
                                        System.out.print("Enter session ID: ");
                                        UUID id = UUID.fromString(scanner.nextLine());
                                        Optional<GroupSession> sessionOpt = groupSessionRepo.findById(id);
                                        if (sessionOpt.isPresent()) {
                                            GroupSession session = sessionOpt.get();
                                            session.addParticipant(currentUser);
                                            currentUser.reserveSession(session);
                                            groupSessionRepo.save(session);
                                            System.out.println("You have successfully joined the session!");
                                        } else {
                                            System.out.println("Wrong session ID!");
                                        }
                                    } else {
                                        System.out.println("Membership Inactive!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter session ID:");
                                    UUID id = UUID.fromString(scanner.nextLine());
                                    Optional<GroupSession> sessionOpt = groupSessionRepo.findById(id);
                                    if (sessionOpt.isPresent()) {
                                        GroupSession session = sessionOpt.get();
                                        currentUser.cancelReservation(session);
                                        session.removeParticipant(currentUser.getId());
                                        groupSessionRepo.save(session);
                                    } else {
                                        System.out.println("Wrong session ID!");
                                    }
                                    break;
                                case 3:
                                default:
                                    break;
                            }
                            break;
                        case 4: // Add cash
                            System.out.print("How much do you wanna add? ");
                            double addCash = 0;
                            try {
                                addCash = Double.parseDouble(scanner.nextLine().replace(",", "."));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid amount entered.");
                                break;
                            }
                            currentUser.setWallet(currentUser.getWallet() + addCash);
                            System.out.println("Added " + addCash + " to your wallet.");
                            break;
                        case 5: // Show trainers
                            for (Trainer trainer : trainerRepo.findAll()) {
                                System.out.println(trainer.toString());
                            }
                            break;
                        case 6: // Logout
                            break;
                        case 7: // Add Trainer (ADMIN)
                            if (currentUser.getRole().equals(ROLE.ADMIN)) {
                                System.out.println("Enter trainer name");
                                String name = scanner.nextLine();
                                System.out.println("Enter trainer's specialities separated with ',': ");
                                List<String> specialities = Arrays.asList(scanner.nextLine().split(","));
                                Trainer trainer = new Trainer(name, specialities);
                                trainerRepo.addTrainer(trainer);
                            }
                            break;
                        case 8: // Delete Trainer (ADMIN)
                            if (currentUser.getRole().equals(ROLE.ADMIN)) {
                                System.out.print("Enter trainer ID: ");
                                UUID tid = UUID.fromString(scanner.nextLine());
                                if (groupSessionRepo.findByTrainerId(tid).isEmpty()) {
                                    trainerRepo.removeTrainer(tid);
                                } else {
                                    System.out.println("First delete this trainer from their Group Sessions");
                                }
                            }
                            break;
                        case 9: // Add Group Session (ADMIN)
                            if (currentUser.getRole().equals(ROLE.ADMIN)) {
                                System.out.print("Enter trainer ID: ");
                                UUID tid = UUID.fromString(scanner.nextLine());
                                if (trainerRepo.findById(tid).isPresent()) {
                                    System.out.print("Enter session capacity: ");
                                    int cap = scanner.nextInt();
                                    scanner.nextLine();
                                    GroupSession session = new GroupSession(trainerRepo.findById(tid).get(), cap);
                                    groupSessionRepo.addSession(session);
                                } else {
                                    System.out.println("Invalid Trainer ID!");
                                }
                            }
                            break;
                        case 10: // Delete Group Session (ADMIN)
                            if (currentUser.getRole().equals(ROLE.ADMIN)) {
                                System.out.print("Enter Group Session ID: ");
                                UUID gid = UUID.fromString(scanner.nextLine());
                                Optional<GroupSession> groupSessionOpt = groupSessionRepo.findById(gid);
                                if (groupSessionOpt.isPresent()) {
                                    GroupSession session = groupSessionOpt.get();
                                    for (User user : userRepo.findAll()) {
                                        if (user.getReservations().stream().anyMatch(r -> r.getGroupSession().equals(session))) {
                                            user.cancelReservation(session);
                                        }
                                    }
                                    groupSessionRepo.deleteSession(gid);
                                } else {
                                    System.out.println("Invalid Session ID!");
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                } while (userChoice != 6); // 6 == Logout
            }
            currentUser.logExit();
            currentUser = null;
        }
    }
}