package main.java.com.university.cli;

import main.java.com.university.model.*;
import main.java.com.university.registry.RegistrySystem;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public final class SystemFunctions {
    static Scanner s = new Scanner(System.in);

    public static String login(){
        String choice;
        System.out.print("Login or Exit ? : ");
        while(true){
            try {
                choice = s.nextLine().toLowerCase();
                if(!Objects.equals(choice, "login") && !Objects.equals(choice, "exit")){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.print("Invalid option, Please try again: ");
            }
        }
        return choice.toLowerCase();
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
                s.nextLine(); // Flush the trailing newline left by nextInt()

                System.out.print("Enter password: ");
                password = s.nextLine();

                user = RegistrySystem.userRegistry.get(id);
                if(user == null || !user.validateCredentials(password, id)){
                    throw new InputMismatchException();
                }
                validated = true;
                break; // Break the retry loop on success
            } catch(InputMismatchException e){
                System.out.println("Wrong credentials, Please try again.");
                s.nextLine(); // Clear the buffer to prevent infinite loop if input was text
            }
        }
        return (validated ? user : null);
    }

    public static int adminMenu(){
        System.out.println("\n===========================================");
        System.out.println("========       Welcome Admin       ========");
        System.out.println("===========================================\n");

        System.out.println("1. Add user");
        System.out.println("2. Add Course");
        System.out.println("3. Assign Professor to Course");
        System.out.println("4. Log out\n");

        System.out.print("Enter Operation Number [1,2,3,4,5]: ");
        int choice;
        while(true){
            try {
                choice = s.nextInt();
                s.nextLine(); // Flush trailing newline
                if(choice < 1 || choice > 5){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.print("Invalid operation, Please Try again: ");
                s.nextLine(); // Clear bad input buffer
            }
        }
        return choice;
    }

    public static void addUser(){
        System.out.println("Please enter user details");
        String name, email, password, role;
        while(true){
            try {
                System.out.print("Enter name: ");
                name = s.nextLine();
                System.out.print("Enter email: ");
                email = s.nextLine();
                System.out.print("Enter password: ");
                password = s.nextLine();
                System.out.print("Enter role (PROFESSOR/STUDENT): ");
                role = s.nextLine().toUpperCase();
                if(!role.equals("PROFESSOR") && !role.equals("STUDENT")){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, role details should match the options.");
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
                s.nextLine(); // Flush trailing newline

                if(maxCapacity < 20 || credits < 2){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid course information. Try again.");
                s.nextLine(); // Clear bad input buffer
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
                s.nextLine(); // Flush trailing newline

                System.out.print("Enter Course code: ");
                code = s.nextLine();
                if(id < 1){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, Please try again.");
                s.nextLine(); // Clear bad input buffer
            }
        }
        Professor prof = (Professor) RegistrySystem.userRegistry.get(id);
        prof.assignCourse(RegistrySystem.getCourse(code));
    }

    public static int profMenu(){
        System.out.println("\n===========================================");
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
                s.nextLine(); // Flush trailing newline
                if(choice < 1 || choice > 3){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.print("Invalid operation, Please Try again: ");
                s.nextLine(); // Clear bad input buffer
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
                s.nextLine(); // Flush trailing newline
                if(id < 1){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID:");
                s.nextLine(); // Clear bad input buffer
            }
        }
        Professor prof = (Professor) RegistrySystem.userRegistry.get(id);
        for(var course : prof.getTeachingSchedule()){
            System.out.printf("title : %s, course code : %s\n", course.getTitle(), course.getCourseCode());
        }
    }

    public static void assignGrade(){
        int sId, profId;
        String courseCode;
        int marks;
        while(true){
            try {
                System.out.print("Enter your ID, Professor: ");
                profId = s.nextInt();
                System.out.println("Enter student ID, course code, and marks");
                System.out.print("ID: ");
                sId = s.nextInt();
                s.nextLine(); // Flush trailing newline before nextLine()

                System.out.print("Course code: ");
                courseCode = s.nextLine();

                System.out.print("Marks: ");
                marks = s.nextInt();
                s.nextLine(); // Flush trailing newline

                if(sId < 1 || profId < 1 || marks < 0){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, Please try again.");
                s.nextLine(); // Clear bad input buffer
            }
        }

        Professor prof = (Professor) RegistrySystem.userRegistry.get(profId);
        Student stu = (Student) RegistrySystem.userRegistry.get(sId);
        Course course = RegistrySystem.courseRegistry.get(courseCode);
        prof.submitGrade(stu, course, marks);
    }

    public static int stuMenu(){
        System.out.println("\n===========================================");
        System.out.println("=========     Welcome Student     =========");
        System.out.println("===========================================\n");

        System.out.println("1. View available courses");
        System.out.println("2. Register a course");
        System.out.println("3. View transcript");
        System.out.println("4. Log out\n");

        System.out.print("Enter Operation Number [1,2,3,4]: ");
        int choice;
        while(true){
            try {
                choice = s.nextInt();
                s.nextLine(); // Flush trailing newline
                if(choice < 1 || choice > 4){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.print("Invalid operation, Please Try again: ");
                s.nextLine(); // Clear bad input buffer
            }
        }
        return choice;
    }

    public static void viewAvailableCourses(){
        RegistrySystem.getAllCourses();
    }

    public static void registerCourse(){
        int sId;
        String courseCode;
        while(true){
            try {
                System.out.print("Enter your ID, Student: ");
                sId = s.nextInt();
                s.nextLine(); // Flush trailing newline before nextLine()

                System.out.print("Enter course code: ");
                courseCode = s.nextLine();
                if(sId < 1){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, Please try again");
                s.nextLine(); // Clear bad input buffer
            }
        }
        Student stu = (Student) RegistrySystem.userRegistry.get(sId);
        Course course = RegistrySystem.courseRegistry.get(courseCode);
        stu.registerCourse(course);
    }

    public static void viewTranscript(){
        int sId;
        while(true){
            try {
                System.out.print("Enter your ID, Student: ");
                sId = s.nextInt();
                s.nextLine(); // Flush trailing newline
                if(sId < 1){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException e){
                System.out.println("Invalid information, Please try again");
                s.nextLine(); // Clear bad input buffer
            }
        }
        Student stu = (Student) RegistrySystem.userRegistry.get(sId);
        var transcript = stu.getTranscript();
        for(var course : transcript.keySet()){
            System.out.printf("%s : %d\n", course, transcript.get(course));
        }
    }
}