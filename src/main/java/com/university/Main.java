package main.java.com.university;

import main.java.com.university.model.Course;
import main.java.com.university.model.Professor;
import main.java.com.university.model.Student;
import main.java.com.university.registry.RegistrySystem;

class Main {
    public static void main(String[] args){
        System.out.println("Initializing University Management System Engine...");
        Professor drAhmed    = new Professor("Ahmed Ahmed", "Ahmed@gmail.com", "offonoffon");
        Student   mazen     = new Student("Mazen Mazen", "mazne@gmail.com", "iusearchbtw");
        Course    convexOpt    = new Course("AI-135", "Convex Optimization Theory", 3, 500);
        Professor drYasser    = new Professor("Yasser Yasser", "Yasser@gmail.com", "123456");
        Student   ibrahim     = new Student("Mazen Mazen", "ibrahim@gmail.com", "654321");
        Course    math2    = new Course("M-101", "Math 2", 3, 300);

        drAhmed.assignCourse(convexOpt);
        drYasser.assignCourse(math2);
        mazen.registerCourse(convexOpt);
        mazen.registerCourse(math2);
        ibrahim.registerCourse(convexOpt);
        ibrahim.registerCourse(math2);
        drYasser.submitGrade(mazen, math2, 100);
        drYasser.submitGrade(ibrahim, math2, 77);
        drAhmed.submitGrade(mazen, convexOpt, 89);
        drAhmed.submitGrade(ibrahim, convexOpt, 92);

        RegistrySystem.getAllCourses();
        RegistrySystem.getAllUsers();
    }
}