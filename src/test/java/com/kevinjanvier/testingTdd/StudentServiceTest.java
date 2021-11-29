package com.kevinjanvier.testingTdd;

import com.kevinjanvier.testingTdd.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("returningSavedStudentfromService")
    void returningSavedStudentfromService() {

        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        //when
       var student = studentService.getStudentById(savedStudent.getId());

        //then
        then(student.getName()).isEqualTo("Mark");
    }
}
