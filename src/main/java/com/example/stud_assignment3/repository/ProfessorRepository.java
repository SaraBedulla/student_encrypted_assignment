package com.example.stud_assignment3.repository;

import com.example.stud_assignment3.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
