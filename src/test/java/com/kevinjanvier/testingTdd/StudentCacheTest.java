package com.kevinjanvier.testingTdd;

import com.kevinjanvier.testingTdd.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentCacheTest {


    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;


    @Test
    @DisplayName("getStudentById_forMultipleRequests")
    void getStudentByIdForMultipleRequests() {

        //given
        Long id = 123l;
        given(studentRepository.findById(id))
                .willReturn(Optional.of(new Student(null,"Mark")));

       //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);

        //then
        then(studentRepository).should(times(1)).findById(id);

    }
}
