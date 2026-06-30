package main.java.com.university.model;

import main.java.com.university.registry.RegistrySystem;

import java.util.Objects;

public class Admin extends User{
    public Admin(String name, String email, String password) {
        super(name, email, password, Role.ADMIN);
    }

    public User addUser(String name, String email, String password, Role role){
        if(Objects.equals(role, Role.PROFESSOR)){
            return new Professor(name,email,password);
        } else {
            return new Student(name,email,password);
        }
    }

    public Course addCourse(String courseCode, String title, int credits, int maxCapacity){
        return new Course(courseCode,title,credits,maxCapacity);
    }

    public void removeUser(int id){
        Student student = (Student) RegistrySystem.userRegistry.get(id);
        for(var course : RegistrySystem.courseRegistry.values()){
            course.removeStudent(student);
        }
        RegistrySystem.userRegistry.remove(id);
    }

    public void removeCourse(int id){
        Course course = (Course) RegistrySystem.courseRegistry.get(id);
        for(User user : RegistrySystem.userRegistry.values()){
            var professor = (Professor) user;
            var courses = professor.getTeachingSchedule();
            courses.remove(id);
        }
        RegistrySystem.userRegistry.remove(id);
    }
}
