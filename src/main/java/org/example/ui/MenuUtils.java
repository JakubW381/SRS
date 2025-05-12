package org.example.ui;

import org.example.model.User;

import java.util.List;

public class MenuUtils {
    public static void displayTitle(String title, User currentUser) {
        System.out.println();
        int frameLength = 80;
        String frame = "#".repeat(frameLength);
        String titlePadding = " ".repeat((frameLength - 2 - title.length()) / 2);
        System.out.println(frame);
        if (title.length() % 2 == 0) {
            System.out.println("#" + titlePadding + title.toUpperCase() + titlePadding + "#");
        } else {
            System.out.println("#" + titlePadding + title.toUpperCase() + titlePadding + " #");
        }
        if (currentUser != null) {
            String userInfo = "Logged in as: " + currentUser.getName() + " (" + currentUser.getRole() + ")" + " | Balance: " + currentUser.getWallet();
            String userInfoPadding = " ".repeat((frameLength - 2 - userInfo.length()) / 2);
            if (userInfo.length() % 2 == 0) {
                System.out.println("#" + userInfoPadding + userInfo + userInfoPadding + "#");
            } else {
                System.out.println("#" + userInfoPadding + userInfo + userInfoPadding + " #");
            }
        }
        System.out.println(frame);
    }

    public static void displayMenu(String title, List<String> options, User currentUser) {
        displayTitle(title, currentUser);
        for (String option : options) {
            System.out.println("\t" + option);
        }
        System.out.print("Enter your choice: ");
    }
}
