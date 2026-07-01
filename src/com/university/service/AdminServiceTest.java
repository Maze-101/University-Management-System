package com.university.service;

import main.java.com.university.model.Admin;
import main.java.com.university.model.Course;
import main.java.com.university.model.Professor;
import main.java.com.university.model.Role;
import main.java.com.university.model.Student;
import main.java.com.university.model.User;
import main.java.com.university.registry.RegistrySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        RegistrySystem.userRegistry.clear();
        RegistrySystem.courseRegistry.clear();

        Field counter = User.class.getDeclaredField("counter");
        counter.setAccessible(true);
        counter.setInt(null, 1);
    }

    @Test
    void constructorInitializesAdminAndRegistersUser() {
        Admin admin = new Admin("Registrar", "admin@university.edu", "secret");

        assertEquals(1, admin.getId());
        assertEquals("Registrar", admin.getName());
        assertEquals("admin@university.edu", admin.getEmail());
        assertEquals(Role.ADMIN, admin.getRole());
        assertSame(admin, RegistrySystem.getUser(admin.getId()));
    }

    @Test
    void addUserCreatesProfessorWhenRoleIsProfessor() {
        Admin admin = new Admin("Registrar", "admin@university.edu", "secret");

        User user = admin.addUser("Dr. Smith", "smith@university.edu", "secret", Role.PROFESSOR);

        assertInstanceOf(Professor.class, user);
        assertEquals(Role.PROFESSOR, user.getRole());
        assertSame(user, RegistrySystem.getUser(user.getId()));
    }

    @Test
    void addUserCreatesStudentForStudentRole() {
        Admin admin = new Admin("Registrar", "admin@university.edu", "secret");

        User user = admin.addUser("Alice", "alice@university.edu", "secret", Role.STUDENT);

        assertInstanceOf(Student.class, user);
        assertEquals(Role.STUDENT, user.getRole());
        assertSame(user, RegistrySystem.getUser(user.getId()));
    }

    @Test
    void addCourseCreatesAndRegistersCourse() {
        Admin admin = new Admin("Registrar", "admin@university.edu", "secret");

        Course course = admin.addCourse("CS101", "Intro to Programming", 3, 40);

        assertEquals("CS101", course.getCourseCode());
        assertEquals("Intro to Programming", course.getTitle());
        assertEquals(3, course.getCredits());
        assertSame(course, RegistrySystem.getCourse("CS101"));
    }

    @Test
    void removeUserRemovesStudentFromRegistryAndCourses() {
        Admin admin = new Admin("Registrar", "admin@university.edu", "secret");
        Student student = (Student) admin.addUser("Alice", "alice@university.edu", "secret", Role.STUDENT);
        Course course = admin.addCourse("CS101", "Intro to Programming", 3, 1);
        student.registerCourse(course);

        admin.removeUser(student.getId());

        assertNull(RegistrySystem.getUser(student.getId()));
        assertFalse(course.isFull());
    }

    @Test
    void removeCourseRemovesCourseFromRegistryProfessorScheduleAndStudentCourses() {
        Admin admin = new Admin("Registrar", "admin@university.edu", "secret");
        Professor professor = (Professor) admin.addUser("Dr. Smith", "smith@university.edu", "secret", Role.PROFESSOR);
        Student student = (Student) admin.addUser("Alice", "alice@university.edu", "secret", Role.STUDENT);
        Course course = admin.addCourse("CS101", "Intro to Programming", 3, 1);
        professor.assignCourse(course);
        student.registerCourse(course);

        RegistrySystem.userRegistry.remove(admin.getId());
        admin.removeCourse(course.getCourseCode());

        assertNull(RegistrySystem.getCourse(course.getCourseCode()));
        assertFalse(professor.getTeachingSchedule().contains(course));
        assertFalse(student.getCourses().contains(course));
    }
}
