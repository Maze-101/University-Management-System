package com.university.service;

import main.java.com.university.model.Course;
import main.java.com.university.model.Role;
import main.java.com.university.model.Student;
import main.java.com.university.model.User;
import main.java.com.university.registry.RegistrySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        RegistrySystem.userRegistry.clear();
        RegistrySystem.courseRegistry.clear();

        Field counter = User.class.getDeclaredField("counter");
        counter.setAccessible(true);
        counter.setInt(null, 1);
    }

    @Test
    void constructorInitializesStudentAndRegistersUser() {
        Student student = new Student("Alice", "alice@university.edu", "secret");

        assertEquals(1, student.getId());
        assertEquals("Alice", student.getName());
        assertEquals("alice@university.edu", student.getEmail());
        assertEquals(Role.STUDENT, student.getRole());
        assertTrue(student.getCourses().isEmpty());
        assertTrue(student.getTranscript().isEmpty());
        assertSame(student, RegistrySystem.getUser(student.getId()));
    }

    @Test
    void validateCredentialsMatchesIdAndPassword() {
        Student student = new Student("Alice", "alice@university.edu", "secret");

        assertTrue(student.validateCredentials("secret", student.getId()));
        assertFalse(student.validateCredentials("wrong", student.getId()));
        assertFalse(student.validateCredentials("secret", student.getId() + 1));
    }

    @Test
    void registerCourseAddsCourseAndUpdatesCourseCapacity() {
        Student student = new Student("Alice", "alice@university.edu", "secret");
        Course course = new Course("CS101", "Intro to Programming", 3, 1);

        student.registerCourse(course);

        assertTrue(student.getCourses().contains(course));
        assertTrue(course.isFull());
    }

    @Test
    void dropCourseRemovesCourseAndUpdatesCourseCapacity() {
        Student student = new Student("Alice", "alice@university.edu", "secret");
        Course course = new Course("CS101", "Intro to Programming", 3, 1);
        student.registerCourse(course);

        student.dropCourse(course);

        assertFalse(student.getCourses().contains(course));
        assertFalse(course.isFull());
    }
}
