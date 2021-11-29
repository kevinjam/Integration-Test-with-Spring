package com.kevinjanvier.testingTdd;

import com.kevinjanvier.testingTdd.service.StudentService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("returningSavedStudentFromService")
    void returningSavedStudentFromService() {

        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        //when
       var student = studentService.getStudentById(savedStudent.getId());

        //then
        then(student.getName()).isEqualTo("Mark");
    }

    @Test
    @DisplayName("getStudentById_whenMissingStudent_notFoundExceptionThrown")
    void getStudentByIdWhenMissingStudentNotFoundExceptionThrown() {

        //given
        Long id = 123L;

        //when
        Throwable throwable =catchThrowable(() ->studentService.getStudentById(id));

        //then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
        
    }
}
