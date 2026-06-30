package main.java.com.university.model;

import java.util.HashMap;
import java.util.HashSet;

public class Student extends User {
    private HashSet<Course> courses = new HashSet<Course>();
    private HashMap<String, Integer> academicTranscript = new HashMap<String, Integer>();

    public Student(String name, String email, String password) {
        super(name, email, password, Role.STUDENT);
    }

    public void registerCourse(Course course){
        this.courses.add(course);
        course.addStudent(this);
    }

    public void dropCourse(Course course){
        this.courses.remove(course);
        course.removeStudent(this);
    }

    public HashMap<String, Integer> getTranscript(){
        return this.academicTranscript;
    }
}