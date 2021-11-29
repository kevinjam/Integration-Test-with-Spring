package com.kevinjanvier.student;

import com.kevinjanvier.student.service.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@WebMvcTest
public class StudentControllerBaseClass {

    @MockBean
    private StudentService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void before() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);

        given(service.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1L)
                        .name("Mark")
                        .grade(10)
                        .build()
        );

    }
}
