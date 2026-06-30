package main.java.com.university.model;

import main.java.com.university.registry.RegistrySystem;

import java.util.ArrayList;

public class Course {
    private final String courseCode;
    private final String title;
    private final int credits;
    private final int maxCapacity;
    private Professor instructor;
    private ArrayList<Student> enrolledStudents = new ArrayList<Student>();

    public Course(String courseCode, String title, int credits, int maxCapacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        RegistrySystem.addCourse(this);
    }

    public String getCourseCode(){
        return this.courseCode;
    }

    public String getTitle(){
        return this.title;
    }

    public Professor getInstructor(){
        return this.instructor;
    }

    public int getCredits(){
        return this.credits;
    }

    public void addStudent(Student student){
        enrolledStudents.add(student);
    }

    public void removeStudent(Student student){
        enrolledStudents.remove(student);
    }

    public void setInstructor(Professor professor){
        this.instructor = professor;
    }

    public boolean isFull(){
        return enrolledStudents.size() == maxCapacity;
    }
}