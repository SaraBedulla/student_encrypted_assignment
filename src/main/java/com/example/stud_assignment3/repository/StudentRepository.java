package com.example.stud_assignment3.repository;

import com.example.stud_assignment3.entity.Student;
import com.example.stud_assignment3.service.EncryptionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findStudentByEmail(String email);





}
