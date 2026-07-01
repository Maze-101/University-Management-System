package main.java.com.university.cli;

import main.java.com.university.model.*;
import main.java.com.university.registry.RegistrySystem;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public final class InputReader {
    static Scanner s = new Scanner(System.in);
    static String choice = "";
    static boolean incorrect;
    public static String login(){
        int counter = 0;
        System.out.print("Login or Exit ? : ");
        while(counter++ < 3){
            try {
                InputReader.choice = s.nextLine().toLowerCase();
                if(!Objects.equals(InputReader.choice, "login") && !Objects.equals(InputReader.choice, "exit")){
                    throw new InputMismatchException();
                }
            } catch(InputMismatchException e){
                System.out.print("Invalid option, Please try again: ");
            }
        }

        return InputReader.choice;
    }

    public static User userValidation(){
        int id;
        String password;
        int counter = 0;
        User user = null;
        boolean validated = false;
        while(counter++ < 3){
            try {
                System.out.print("Enter ID: ");
                id = s.nextInt();
                System.out.print("Enter password");
                password = s.nextLine();
                user = RegistrySystem.userRegistry.get(id);
                if(user == null || !user.validateCredentials(password, id)){
                    throw new InputMismatchException();
                }
                validated = true;
            } catch(InputMismatchException e){
                System.out.println("Wrong credentials, Please try again.");
            }
        }
        return (validated ? user : null);
    }

    public static int adminMenu(){
        System.out.println("===========================================");
        System.out.println("========       Welcome Admin       ========");
        System.out.println("===========================================\n");

        System.out.println("1. Add Student");
        System.out.println("2. Add Professor");
        System.out.println("3. Add Course");
        System.out.println("4. Assign Professor to Course");
        System.out.println("5. Log out\n");

        System.out.print("Enter Operation Number [1,2,3,4,5]: ");
        int choice;
        while(true){
            try {
                choice = s.nextInt();
                if(choice < 1 || choice > 5){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.print("Invalid operation, Please Try again: ");
            }
        }
        return choice;
    }

    public static void addUser(){
        System.out.println("Please enter student details");
        String name, email, password, role;
        while(true){
            try {
                System.out.print("Enter name: ");
                name = s.nextLine();
                System.out.print("Enter email: ");
                email = s.nextLine();
                System.out.print("Enter password: ");
                password = s.nextLine();
                System.out.print("Enter role: ");
                role = s.nextLine().toUpperCase();
                if(!role.equals("PROFESSOR") && !role.equals("STUDENT")){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, student details should be a string.");
            }
        }
        if(role.equals("PROFESSOR")){
            new Professor(name, email, password);
        } else {
            new Student(name, email, password);
        }
    }

    public static void addCourse(){
        System.out.println("Please enter course details");
        String courseCode, title;
        int credits, maxCapacity;
        while(true){
            try {
                System.out.print("Enter code: ");
                courseCode = s.nextLine();
                System.out.print("Enter title: ");
                title = s.nextLine();
                System.out.print("Enter credits: ");
                credits = s.nextInt();
                System.out.print("Enter maxCapacity: ");
                maxCapacity = s.nextInt();
                if(maxCapacity < 20 && credits < 2){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid course information");
            }
        }
        new Course(courseCode, title, credits, maxCapacity);
    }

    public static void assignProfToCourse(){
        System.out.println("Please enter Professor ID and Course code");
        int id; String code;
        while(true){
            try {
                System.out.print("Enter Professor ID: ");
                id = s.nextInt();
                System.out.print("Enter Course code: ");
                code = s.nextLine();
                if(id < 1){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, Please try again.");
            }
        }
        Professor prof = (Professor) RegistrySystem.userRegistry.get(id);
        prof.assignCourse(RegistrySystem.getCourse(code));
    }

    public static int profMenu(){
        System.out.println("===========================================");
        System.out.println("========     Welcome Professor     ========");
        System.out.println("===========================================\n");

        System.out.println("1. View assigned courses");
        System.out.println("2. Assign grade to a student");
        System.out.println("3. Log out\n");

        System.out.print("Enter Operation Number [1,2,3]: ");
        int choice;
        while(true){
            try {
                choice = s.nextInt();
                if(choice < 1 || choice > 3){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.print("Invalid operation, Please Try again: ");
            }
        }
        return choice;
    }

    public static void viewAssignedCourses(){
        int id;
        while(true){
            try {
                System.out.print("Enter your ID, Professor: ");
                id = s.nextInt();
                if(id < 1){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID:");
            }
        }
        Professor prof = (Professor) RegistrySystem.userRegistry.get(id);
        for(var course : prof.getTeachingSchedule()){
            System.out.printf("title : %s, course code : %s", course.getTitle(), course.getCourseCode());
        }
    }
}