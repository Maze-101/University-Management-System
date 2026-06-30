package main.java.com.university.cli;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public final class InputReader {
    static Scanner s = new Scanner(System.in);
    static String choice = "";
    static boolean incorrect;
    public static String login(){
        System.out.print("Login or Exit ? : ");
        while(true){
            InputReader.incorrect = true;
            try {
                InputReader.choice = s.nextLine().toLowerCase();
                if(!Objects.equals(InputReader.choice, "login") && !Objects.equals(InputReader.choice, "exit")){
                    throw new InputMismatchException();
                }
                InputReader.incorrect = false;
            } catch(InputMismatchException e){
                System.out.print("Invalid option, Please try again: ");
            }

            if(!InputReader.incorrect){
                break;
            }
        }

        return InputReader.choice;
    }

    public static String roleMenu(){
        System.out.println("===========================================");
        System.out.println("What's your role ?");
        System.out.println("1. Admin");
        System.out.println("2. Professor");
        System.out.println("3. Student\n");
        System.out.print("Role: ");
        while(true){
            InputReader.incorrect = true;
            try {
                InputReader.choice = s.nextLine().toLowerCase();
                if(!InputReader.choice.equals("admin") && !InputReader.choice.equals("student") && !InputReader.choice.equals("professor")){
                    throw new InputMismatchException();
                }
                InputReader.incorrect = false;
            } catch(InputMismatchException e){
                System.out.print("Invalid role, Please try again: ");
            }

            if(!InputReader.incorrect){
                break;
            }
        }

        return choice;
    }
}