package main.java.com.university.model;

import java.util.List;

public class Course {
    private final String courseCode;
    private final String title;
    private final int credits;
    private final int maxCapacity;
    private Professor instructor;
    private List<Student> enrolledStudents;

    public Course(String courseCode, String title, int credits, int maxCapacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
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

    public boolean addStudent(Student student){
        enrolledStudents.add(student);
        return true;
    }

    public boolean removeStudent(Student student){
        enrolledStudents.remove(student);
        return true;
    }

    public void setInstructor(Professor professor){
        this.instructor = professor;
    }

    public boolean isFull(){
        return enrolledStudents.size() == maxCapacity;
    }
}