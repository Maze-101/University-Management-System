package main.java.com.university.cli;

import main.java.com.university.model.User;

import java.util.Objects;

import static main.java.com.university.model.Role.*;

public final class ConsoleManager {
    public static void universityManagementSystem(){
        while(true){
            System.out.println("===========================================");
            System.out.println("======== Welcome to our university ========");
            System.out.println("===========================================\n");

            String choice = SystemFunctions.login();

            if(choice.equals("exit")){
                System.out.println("Exiting System, Goodbye :)");
                break;
            }

            System.out.println("===========================================\n");

            User user = SystemFunctions.userValidation();

            if(user == null){
                break;
            }

            System.out.println("===========================================\n");

            if(Objects.equals(user.getRole(), ADMIN)){
                while(true){
                    int adminChoice = SystemFunctions.adminMenu();

                    System.out.println("===========================================\n");

                    if(adminChoice == 1){
                        SystemFunctions.addUser();
                    } else if(adminChoice == 2) {
                        SystemFunctions.addCourse();
                    } else if (adminChoice == 3) {
                        SystemFunctions.assignProfToCourse();
                    } else {
                        break;
                    }
                }
            } else if (Objects.equals(user.getRole(), PROFESSOR)){
                while(true){
                    int profChoice = SystemFunctions.profMenu();

                    System.out.println("===========================================\n");

                    if(profChoice == 1){
                        SystemFunctions.viewAssignedCourses();
                    } else if(profChoice == 2) {
                        SystemFunctions.assignGrade();
                    } else {
                        break;
                    }
                }
            } else {
                while(true){
                    int stuChoice = SystemFunctions.stuMenu();

                    System.out.println("===========================================\n");

                    if(stuChoice == 1){
                        SystemFunctions.viewAvailableCourses();
                    } else if(stuChoice == 2) {
                        SystemFunctions.registerCourse();
                    } else if (stuChoice == 3) {
                        SystemFunctions.viewTranscript();
                    } else {
                        break;
                    }
                }
            }

        }
    }
}