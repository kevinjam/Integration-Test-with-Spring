package com.kevinjanvier.testingTdd.controller;

import com.kevinjanvier.testingTdd.Student;
import com.kevinjanvier.testingTdd.StudentNotFoundException;
import com.kevinjanvier.testingTdd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void studentNotFoundException(StudentNotFoundException foundException){
    }
}
