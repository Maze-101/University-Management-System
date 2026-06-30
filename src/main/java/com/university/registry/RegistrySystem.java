package main.java.com.university.registry;

import main.java.com.university.model.Course;
import main.java.com.university.model.User;

import java.util.HashMap;

public class RegistrySystem {
    public static HashMap<Integer, User> userRegistry;
    public static HashMap<String, Course> courseRegistry;

    private RegistrySystem() {}

    private static class RegistrySystemHolder {
        private static final RegistrySystem INSTANCE = new RegistrySystem();
    }

    public static Object getInstance() {
        return RegistrySystemHolder.INSTANCE;
    }

    public void addUser(User user){
        userRegistry.put(user.getId(), user);
    }

    public User getUser(int id){
        return userRegistry.get(id);
    }

    public void addCourse(Course course){
        courseRegistry.put(course.getCourseCode(), course);
    }

    public Course getCourse(String courseCode){
        return courseRegistry.get(courseCode);
    }

    public void getAllCourses(){
        for(String code : courseRegistry.keySet()){
            var course = courseRegistry.get(code);
            System.out.printf("- title: %s, code: %s, credits: %d, professor: %s, full: %b", course.getTitle(), course.getCourseCode(), course.getCredits(),course.getInstructor().getName(), course.isFull());
        }
    }

    public void getAllUsers(){
        for(int id : userRegistry.keySet()){
            var user = userRegistry.get(id);
            System.out.printf("- name: %s, id: %s, email: %s, password: %s, role: %s", user.getName(), user.getId(), user.getEmail(),user.getPass(), user.getRole());
        }
    }
}
