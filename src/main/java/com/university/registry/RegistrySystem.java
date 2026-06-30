package main.java.com.university.registry;

import main.java.com.university.model.Course;
import main.java.com.university.model.User;

import java.util.HashMap;

public class RegistrySystem {
    private HashMap<String, User> userRegistry;
    private HashMap<String, Course> courseRegistry;

    public void addUser(User user){
        this.userRegistry.put(user.getId(), user);
    }

    public User getUser(String id){
        return this.userRegistry.get(id);
    }

    public void addCourse(Course course){
        this.courseRegistry.put(course.getCourseCode(), course);
    }

    public Course getCourse(String courseCode){
        return this.courseRegistry.get(courseCode);
    }

    public void getAllCourses(){
        for(String code : this.courseRegistry.keySet()){
            var course = this.courseRegistry.get(code);
            System.out.printf("- title: %s, code: %s, credits: %d, professor: %s, full: %b", course.getTitle(), course.getCourseCode(), course.getCredits(),course.getInstructor().getName(), course.isFull());
        }
    }
}
