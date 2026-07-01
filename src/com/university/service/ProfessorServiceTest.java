package com.university.service;

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

class ProfessorServiceTest {
    @BeforeEach
    void setUp() throws Exception {
        RegistrySystem.userRegistry.clear();
        RegistrySystem.courseRegistry.clear();

        Field counter = User.class.getDeclaredField("counter");
        counter.setAccessible(true);
        counter.setInt(null, 1);
    }

    @Test
    void constructorInitializesProfessorAndRegistersUser() {
        Professor professor = new Professor("Dr. Smith", "smith@university.edu", "secret");

        assertEquals(1, professor.getId());
        assertEquals("Dr. Smith", professor.getName());
        assertEquals("smith@university.edu", professor.getEmail());
        assertEquals(Role.PROFESSOR, professor.getRole());
        assertTrue(professor.getTeachingSchedule().isEmpty());
        assertSame(professor, RegistrySystem.getUser(professor.getId()));
    }

    @Test
    void assignCourseAddsCourseToTeachingScheduleAndSetsInstructor() {
        Professor professor = new Professor("Dr. Smith", "smith@university.edu", "secret");
        Course course = new Course("CS201", "Data Structures", 4, 30);

        professor.assignCourse(course);

        assertTrue(professor.getTeachingSchedule().contains(course));
        assertSame(professor, course.getInstructor());
    }

    @Test
    void assignCourseKeepsMultipleCoursesInSchedule() {
        Professor professor = new Professor("Dr. Smith", "smith@university.edu", "secret");
        Course dataStructures = new Course("CS201", "Data Structures", 4, 30);
        Course algorithms = new Course("CS301", "Algorithms", 4, 25);

        professor.assignCourse(dataStructures);
        professor.assignCourse(algorithms);

        assertEquals(2, professor.getTeachingSchedule().size());
        assertTrue(professor.getTeachingSchedule().contains(dataStructures));
        assertTrue(professor.getTeachingSchedule().contains(algorithms));
    }

    @Test
    void submitGradeStoresMarksByCourseCodeInStudentTranscript() {
        Professor professor = new Professor("Dr. Smith", "smith@university.edu", "secret");
        Student student = new Student("Alice", "alice@university.edu", "secret");
        Course course = new Course("CS201", "Data Structures", 4, 30);

        professor.submitGrade(student, course, 92);

        assertEquals(92, student.getTranscript().get("CS201"));
    }
}
