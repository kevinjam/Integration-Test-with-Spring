package com.kevinjanvier.testingTdd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String mark);
}
