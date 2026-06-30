package main.java.com.university.cli;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

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

            String role = InputReader.roleMenu();

            if(Objects.equals(role, "admin")){

            } else if (Objects.equals(role, "professor")){

            } else {

            }

        }
    }
}