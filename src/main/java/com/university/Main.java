package main.java.com.university;

import main.java.com.university.cli.ConsoleManager;
import main.java.com.university.model.*;
import main.java.com.university.registry.RegistrySystem;

class Main {
    public static void main(String[] args){
        User admin = new Admin("Ahmed Yasser", "ahmed@gmeil.com", "admin");
        ConsoleManager.universityManagementSystem();
    }
}