package com.example.gestionecoles.repository;

import com.example.gestionecoles.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer>, JpaSpecificationExecutor<School> {
    Optional<School> findByEmail(String email);
}
