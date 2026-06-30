package main.java.com.university.model;

import main.java.com.university.model.User;
import main.java.com.university.registry.RegistrySystem;

import java.util.ArrayList;

public class Professor extends User {
    private String department;
    private final ArrayList<Course> teachingSchedule = new ArrayList<>();

    public Professor(String name, String email, String password) {
        super(name, email, password, Role.PROFESSOR);
    }

    public void assignCourse(Course course){
        this.teachingSchedule.add(course);
        course.setInstructor(this);
    }

    public ArrayList<Course> getTeachingSchedule(){
        return this.teachingSchedule;
    }

    public void submitGrade(Student student, Course course, int marks){
        var grades = student.getTranscript();
        grades.put(course.getCourseCode(), marks);
    }
}