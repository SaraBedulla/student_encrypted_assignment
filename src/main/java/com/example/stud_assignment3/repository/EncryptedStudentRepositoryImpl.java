package com.example.stud_assignment3.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EncryptedStudentRepositoryImpl implements EncryptedStudentRepository{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findAllDecryptedNames() {
        Query query = entityManager.createNativeQuery("SELECT decrypt_aesd(name,'encryption_key_h') FROM student");
        return query.getResultList();
    }

}
