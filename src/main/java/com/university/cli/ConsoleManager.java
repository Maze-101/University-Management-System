package main.java.com.university.cli;

import main.java.com.university.model.Admin;
import main.java.com.university.model.User;
import main.java.com.university.registry.RegistrySystem;

import java.util.Objects;

import static main.java.com.university.model.Role.*;

public final class ConsoleManager {
    public static void universityManagementSystem(){
        while(true){
            System.out.println("===========================================");
            System.out.println("======== Welcome to our university ========");
            System.out.println("===========================================\n");

            String choice = InputReader.login();

            if(choice.equals("exit")){
                System.out.println("Exiting System, Goodbye :)");
                break;
            }

            System.out.println("===========================================\n");

            User user = InputReader.userValidation();

            if(user == null){
                break;
            }

            System.out.println("===========================================\n");

            if(Objects.equals(user.getRole(), ADMIN)){
                Admin admin = (Admin) user;
                int adminChoice = InputReader.adminMenu();

                System.out.println("===========================================\n");

                if(adminChoice == 1 || adminChoice == 2){
                    InputReader.userDetails();
                } else if(adminChoice == 3) {

                } else if (adminChoice == 4) {

                } else {

                }
            } else if (Objects.equals(user.getRole(), PROFESSOR)){

            } else {

            }

        }
    }
}