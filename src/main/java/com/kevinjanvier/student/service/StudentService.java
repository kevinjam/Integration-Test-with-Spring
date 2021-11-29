package com.kevinjanvier.student.service;

import com.kevinjanvier.student.Student;
import com.kevinjanvier.student.StudentNotFoundException;
import com.kevinjanvier.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException());
    }
}
