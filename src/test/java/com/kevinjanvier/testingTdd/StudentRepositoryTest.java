package com.kevinjanvier.testingTdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    @DisplayName("testGetStudentByName_returnStudentDetails")
    void testGetStudentByNameReturnStudentDetails() {

        //given
      Student saveStudent =testEntityManager.persist(new Student(null, "mark"));

        //when
        Student student =studentRepository.getStudentByName("mark");

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(saveStudent.getName());

    }

    @Test
    @DisplayName("getAvgGradeForACTIVESTUDENTS__CALCULATEAVG")
    void getAvgGradeForActivestudentsCalculateavg() {
        //given
        Student mark = Student.builder().name("Mark").active(true).grade(80).build();
        Student susan = Student.builder().name("Susan").active(true).grade(100).build();
        Student peter = Student.builder().name("Peter").active(false).grade(50).build();
        Arrays.asList(mark, susan, peter).forEach(testEntityManager::persistAndFlush);

        //when
        var avgGrade = studentRepository.getAvgGradeForActiveStudents();

        //then
        then(avgGrade).isEqualTo(90.0);
    }
}
