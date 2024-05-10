package com.example.stud_assignment3.repository;

import com.example.stud_assignment3.entity.Exam;
import com.example.stud_assignment3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    List<Exam> findByStudentsId(Long studentId);

}
