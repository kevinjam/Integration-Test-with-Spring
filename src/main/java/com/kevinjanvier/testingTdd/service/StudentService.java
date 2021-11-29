package com.kevinjanvier.testingTdd.service;

import com.kevinjanvier.testingTdd.Student;
import com.kevinjanvier.testingTdd.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
