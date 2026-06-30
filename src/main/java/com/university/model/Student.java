package main.java.com.university.model;

import main.java.com.university.model.User;

import java.util.HashMap;
import java.util.HashSet;

public class Student extends User {
    private HashSet<Course> courses;
    private HashMap<String, Integer> academicTranscript;

    public Student(String id, String name, String email, String password, Role role) {
        super(id, name, email, password, Role.STUDENT);
    }

    public boolean registerCourse(Course course){
        courses.add(course);
        return true;
    }

    public boolean dropCourse(Course course){
        courses.remove(course);
        return true;
    }

    public HashMap<String, Integer> getTranscript(){
        return this.academicTranscript;
    }
}