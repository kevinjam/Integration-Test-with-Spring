package com.kevinjanvier.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String mark);

    @Query("select avg (grade) from Student where active=true")
    Double getAvgGradeForActiveStudents();
}
