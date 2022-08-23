package com.luv2code.springmvc;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradesDao;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradesDao mathGradeDao;

    @Autowired
    private ScienceGradesDao scienceGradeDao;

    @Autowired
    private HistoryGradesDao historyGradeDao;

    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute("INSERT INTO STUDENT(id, firstname, lastname, email_address) " +
                "values (1, 'Vlad', 'Danilov', 'vlad.danilov@gmail.com')");

        jdbc.execute("INSERT INTO math_grade(id, student_id, grade) values (1, 1, 100.00)");
        jdbc.execute("INSERT INTO science_grade(id, student_id, grade) values (1, 1, 100.00)");
        jdbc.execute("INSERT INTO history_grade(id, student_id, grade) values (1, 1, 100.00)");
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("DELETE FROM student");
        jdbc.execute("DELETE FROM math_grade");
        jdbc.execute("DELETE FROM science_grade");
        jdbc.execute("DELETE FROM history_grade");
    }


    @Test
    public void createStudentService() {

        studentService.createStudent("Vladislav", "Danilov", "vlad123.danilov@gmail.com");

        CollegeStudent student = studentDao.findByEmailAddress("vlad123.danilov@gmail.com");

        assertEquals("vlad123.danilov@gmail.com", student.getEmailAddress(), "find by email");

    }

    @Sql("/insertData.sql")
    @Test
    public void getGradebookService() {
        Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent : iterableCollegeStudents) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(5, collegeStudents.size());
    }

    @Test
    public void createGradeService() {

        // Create the grade
            assertTrue(studentService.createGrade(90.2, 1, "math"));
            assertTrue(studentService.createGrade(90.2, 1, "science"));
            assertTrue(studentService.createGrade(90.2, 1, "history"));

        // Get all grades with studentId
            Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(1);
            Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
            Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(1);

        // Verify there is grades
        assertTrue(((Collection<MathGrade>)mathGrades).size() == 2, "Student has math grades");
        assertTrue(((Collection<ScienceGrade>)scienceGrades).size() == 2, "Student has science grades");
        assertTrue(((Collection<HistoryGrade>)historyGrades).size() == 2, "Student has history grades");
    }

    @Test
    public void deleteGradeService() {
        assertEquals(1, studentService.deleteGrade(1, "math"), "Returns student id after");
        assertEquals(1, studentService.deleteGrade(1, "science"), "Returns student id after");
        assertEquals(1, studentService.deleteGrade(1, "history"), "Returns student id after");
    }

    @Test
    public void deleteGradeServiceReturnStudentIdOfZero() {
        assertEquals(0, studentService.deleteGrade(0, "science"), "No student should have 0 id");
        assertEquals(0, studentService.deleteGrade(1, "literature"), "No student should have a literature class");
    }

    @Test
    public void isStudentIdExistsCheck() {

        assertTrue(studentService.checkIfStudentExists(1));

        assertFalse(studentService.checkIfStudentExists(0));
    }

    @Test
    public void deleteStudentService() {
        Optional<CollegeStudent> deletedCollegeStudent =studentDao.findById(1);
        Optional<MathGrade> deletedMathGrade = mathGradeDao.findById(1);
        Optional<HistoryGrade> deletedHistoryGrade = historyGradeDao.findById(1);
        Optional<ScienceGrade> deletedScienceGrade = scienceGradeDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return true");
        assertTrue(deletedMathGrade.isPresent(), "Return true");
        assertTrue(deletedHistoryGrade.isPresent(), "Return true");
        assertTrue(deletedScienceGrade.isPresent(), "Return true");


        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);
        deletedMathGrade = mathGradeDao.findById(1);
        deletedHistoryGrade = historyGradeDao.findById(1);
        deletedScienceGrade = scienceGradeDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return false");
        assertFalse(deletedMathGrade.isPresent(), "Return false");
        assertFalse(deletedHistoryGrade.isPresent(), "Return false");
        assertFalse(deletedScienceGrade.isPresent(), "Return false");

    }

    @Test
    public void createGradeServiceReturnsFalse() {
        assertFalse(studentService.createGrade(105, 1, "math"));
        assertFalse(studentService.createGrade(-5, 1 , "match"));
        assertFalse(studentService.createGrade(80.5, 2, "math"));
        assertFalse(studentService.createGrade(80.5, 1, "literature"));
    }

    @Test
    public void studentInformation(){

        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent);
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Vlad", gradebookCollegeStudent.getFirstname());
        assertEquals("Danilov", gradebookCollegeStudent.getLastname());
        assertEquals("vlad.danilov@gmail.com", gradebookCollegeStudent.getEmailAddress());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size()==1);
    }

    @Test
    public void studentInformationServiceReturnNull() {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(0);

        assertNull(gradebookCollegeStudent);
    }
}
