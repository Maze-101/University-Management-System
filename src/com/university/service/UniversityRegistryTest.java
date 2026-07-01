package com.university.service;

import main.java.com.university.model.Course;
import main.java.com.university.model.Professor;
import main.java.com.university.model.Student;
import main.java.com.university.model.User;
import main.java.com.university.registry.RegistrySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UniversityRegistryTest {
    @BeforeEach
    void setUp() throws Exception {
        RegistrySystem.userRegistry.clear();
        RegistrySystem.courseRegistry.clear();

        Field counter = User.class.getDeclaredField("counter");
        counter.setAccessible(true);
        counter.setInt(null, 1);
    }

    @Test
    void getInstanceAlwaysReturnsSameRegistrySystemObject() {
        Object first = RegistrySystem.getInstance();
        Object second = RegistrySystem.getInstance();

        assertSame(first, second);
    }

    @Test
    void userConstructorAddsUserToRegistryAndGetUserReturnsIt() {
        Student student = new Student("Alice", "alice@university.edu", "secret");

        assertSame(student, RegistrySystem.getUser(student.getId()));
        assertEquals(1, RegistrySystem.userRegistry.size());
    }

    @Test
    void courseConstructorAddsCourseToRegistryAndGetCourseReturnsIt() {
        Course course = new Course("CS101", "Intro to Programming", 3, 40);

        assertSame(course, RegistrySystem.getCourse("CS101"));
        assertEquals(1, RegistrySystem.courseRegistry.size());
    }

    @Test
    void registryStoresMultipleUsersAndCourses() {
        Student student = new Student("Alice", "alice@university.edu", "secret");
        Professor professor = new Professor("Dr. Smith", "smith@university.edu", "secret");
        Course programming = new Course("CS101", "Intro to Programming", 3, 40);
        Course databases = new Course("CS202", "Databases", 4, 35);

        assertSame(student, RegistrySystem.getUser(student.getId()));
        assertSame(professor, RegistrySystem.getUser(professor.getId()));
        assertSame(programming, RegistrySystem.getCourse("CS101"));
        assertSame(databases, RegistrySystem.getCourse("CS202"));
        assertEquals(2, RegistrySystem.userRegistry.size());
        assertEquals(2, RegistrySystem.courseRegistry.size());
    }

    @Test
    void getAllUsersPrintsRegisteredUserDetails() {
        new Student("Alice", "alice@university.edu", "secret");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            RegistrySystem.getAllUsers();
        } finally {
            System.setOut(originalOut);
        }

        String printed = output.toString();
        assertTrue(printed.contains("name: Alice"));
        assertTrue(printed.contains("email: alice@university.edu"));
        assertTrue(printed.contains("role: STUDENT"));
    }

    @Test
    void getAllCoursesPrintsRegisteredCourseDetails() {
        Professor professor = new Professor("Dr. Smith", "smith@university.edu", "secret");
        Course course = new Course("CS101", "Intro to Programming", 3, 40);
        professor.assignCourse(course);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            RegistrySystem.getAllCourses();
        } finally {
            System.setOut(originalOut);
        }

        String printed = output.toString();
        assertTrue(printed.contains("title: Intro to Programming"));
        assertTrue(printed.contains("code: CS101"));
        assertTrue(printed.contains("credits: 3"));
        assertTrue(printed.contains("professor: Dr. Smith"));
        assertTrue(printed.contains("full: false"));
    }
}
