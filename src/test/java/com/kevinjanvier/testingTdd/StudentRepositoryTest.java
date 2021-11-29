package com.kevinjanvier.testingTdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Test
    @DisplayName("testGetStudentByName_returnStudentDetails")
    void testGetStudentByNameReturnStudentDetails() {

        //given
      Student saveStudent =studentRepository.save(new Student(null, "mark"));

        //when
        Student student =studentRepository.getStudentByName("mark");

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(saveStudent.getName());

    }
}
