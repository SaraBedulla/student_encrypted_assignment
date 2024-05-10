package com.example.stud_assignment3.repository;

import com.example.stud_assignment3.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User5Repository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByUsername(String username);
}
