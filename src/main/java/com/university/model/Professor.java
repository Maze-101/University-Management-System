package main.java.com.university.model;

import main.java.com.university.model.User;

import java.util.List;

public class Professor extends User {
    private String department;
    private List<Course> teachingSchedule;

    public Professor(String id, String name, String email, String password, Role role) {
        super(id, name, email, password, Role.PROFESSOR);
    }

    private void assignCourse(Course course){
        this.teachingSchedule.add(course);
    }

    private List<Course> getTeachingSchedule(){
        return this.teachingSchedule;
    }

    private void submitGrade(Student student, Course course, int marks){
        var grades = student.getTranscript();
        grades.put(course.getCourseCode(), marks);
    }
}