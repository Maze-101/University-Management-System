package main.java.com.university.model;

import main.java.com.university.model.User;

import java.util.List;

public class Professor extends User {
    private String department;
    private List<Course> teachingSchedule;

    public Professor(String name, String email, String password) {
        super(name, email, password, Role.PROFESSOR);
    }

    public void assignCourse(Course course){
        this.teachingSchedule.add(course);
    }

    public List<Course> getTeachingSchedule(){
        return this.teachingSchedule;
    }

    public void submitGrade(Student student, Course course, int marks){
        var grades = student.getTranscript();
        grades.put(course.getCourseCode(), marks);
    }
}