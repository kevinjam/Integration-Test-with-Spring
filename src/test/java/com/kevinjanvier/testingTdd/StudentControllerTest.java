package com.kevinjanvier.testingTdd;

import com.kevinjanvier.testingTdd.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Test
    @DisplayName("getStudent_ForStudent_IsReturned")
    void getStudentForStudentIsReturned() throws Exception {

        //given
        given(service.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1L)
                        .name("Mark")
                        .grade(10)
                        .build()
        );

        //when
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Mark"))
                .andExpect(jsonPath("grade").value(10));

        //then
    }

    @Test
    @DisplayName("getStudentForMissingStudentStatus404")
    void getStudentForMissingStudentStatus404() throws Exception {

        //give
        given(service.getStudentById(anyLong()))
                .willThrow(StudentNotFoundException.class);        //when

        //then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
