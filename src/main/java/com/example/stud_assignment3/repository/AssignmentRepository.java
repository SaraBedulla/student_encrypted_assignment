package com.example.stud_assignment3.repository;

import com.example.stud_assignment3.entity.Assignment;
import com.example.stud_assignment3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {

}
