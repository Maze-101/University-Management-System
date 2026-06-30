package main.java.com.university.model;

import main.java.com.university.registry.RegistrySystem;

import java.util.Objects;
import java.util.Scanner;

public abstract class User {
    private static int counter = 1;
    private final int id;
    private final String name;
    private String email;
    private String password;
    private final Role role;

    public User(String name, String email, String password, Role role){
        this.id = counter;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        counter++;
        RegistrySystem.addUser(this);
    }

    public boolean validateCredentials(String password, String email){
        return Objects.equals(this.email, email) && Objects.equals(this.password, password);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPass() {
        return this.password;
    }

    public void changeEmail(String newEmail) {
        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("Enter password please: ");
            String in = s.nextLine();
            if(!Objects.equals(this.password, in)){
                System.out.println("Incorrect password!");
            } else {
                this.email = newEmail;
                break;
            }
        }
    }

    public void changePassword(String newPassword) {
        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("Enter password please: ");
            String in = s.nextLine();
            if(!Objects.equals(this.password, in)){
                System.out.println("Incorrect password!");
            } else {
                this.password = newPassword;
                break;
            }
        }
    }
}